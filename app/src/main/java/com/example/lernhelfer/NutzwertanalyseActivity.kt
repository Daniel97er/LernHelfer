package com.example.lernhelfer

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class NutzwertanalyseActivity : AppCompatActivity() {

    data class Alternative(val name: String, val scores: List<Double>)
    data class Criterion(val name: String, val weight: Double)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numCriteriaInput: EditText = findViewById(R.id.num_criteria)
        val criteriaInput: EditText = findViewById(R.id.criteria_input)
        val numAlternativesInput: EditText = findViewById(R.id.num_alternatives)
        val alternativesInput: EditText = findViewById(R.id.alternatives_input)
        val performAnalysisButton: Button = findViewById(R.id.perform_analysis_button)
        val resultsText: TextView = findViewById(R.id.results_text)

        performAnalysisButton.setOnClickListener {
            val criteria = parseCriteria(criteriaInput.text.toString())
            val alternatives = parseAlternatives(alternativesInput.text.toString(), criteria.size)

            val results = performUtilityAnalysis(criteria, alternatives)
            resultsText.text = formatResults(results)
        }
    }

    private fun parseCriteria(input: String): List<Criterion> {
        return input.split(";").map {
            val parts = it.split(",")
            Criterion(parts[0].trim(), parts[1].trim().toDouble())
        }
    }

    private fun parseAlternatives(input: String, numCriteria: Int): List<Alternative> {
        return input.split(";").map {
            val parts = it.split(",")
            val name = parts[0].trim()
            val scores = parts.subList(1, numCriteria + 1).map { score -> score.trim().toDouble() }
            Alternative(name, scores)
        }
    }

    private fun performUtilityAnalysis(criteria: List<Criterion>, alternatives: List<Alternative>): List<Pair<String, Double>> {
        val totalWeight = criteria.sumOf { it.weight }
        val normalizedWeights = criteria.map { it.weight / totalWeight }

        return alternatives.map { alternative ->
            val utilityValue = alternative.scores.zip(normalizedWeights).sumOf { (score, weight) -> score * weight }
            alternative.name to utilityValue
        }.sortedByDescending { it.second }
    }

    private fun formatResults(results: List<Pair<String, Double>>): String {
        return results.joinToString(separator = "\n") { (name, utilityValue) -> "$name: $utilityValue" }
    }
}