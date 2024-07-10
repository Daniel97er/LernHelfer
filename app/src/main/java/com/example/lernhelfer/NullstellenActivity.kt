package com.example.lernhelfer

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction
import org.apache.commons.math3.analysis.solvers.LaguerreSolver
import org.apache.commons.math3.analysis.solvers.PolynomialSolver

class NullstellenActivity : AppCompatActivity() {

    private lateinit var enterFunction: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nullstellen)

        enterFunction = findViewById(R.id.editFunction)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult = findViewById(R.id.textAusgabe)

        calculateButton.setOnClickListener {
            val functionInput = enterFunction.text.toString()
            if (functionInput.isNotEmpty()) {
                val coefficients = parseFunction(functionInput)
                if (coefficients != null) {
                    val roots = calculateRoots(coefficients)
                    textViewResult.text = roots.joinToString(", ", "Die Nullstellen sind: ")
                } else {
                    textViewResult.text = "Bitte geben Sie eine gültige Funktion ein"
                }
            } else {
                textViewResult.text = "Bitte überprüfen Sie Ihre Eingaben"
            }
        }
    }

    private fun parseFunction(function: String): DoubleArray? {
        val regex = Regex("([-+]?[0-9]*\\.?[0-9]+)?\\s*([a-zA-Z]\\^?\\d*)?")
        val matches = regex.findAll(function.replace("\\s".toRegex(), ""))

        val coefficients = mutableListOf<Double>()
        for (match in matches) {
            val (coef, exp) = match.destructured
            if (exp.isNotEmpty()) {
                val exponent = if (exp.contains("^")) exp.split("^")[1].toInt() else 1
                while (coefficients.size <= exponent) {
                    coefficients.add(0.0)
                }
                coefficients[exponent] = coef.toDoubleOrNull() ?: 1.0
            } else if (coef.isNotEmpty()) {
                if (coefficients.isEmpty()) {
                    coefficients.add(coef.toDouble())
                } else {
                    coefficients[0] += coef.toDouble()
                }
            }
        }

        return if (coefficients.isEmpty()) null else coefficients.toDoubleArray().reversedArray()
    }

    private fun calculateRoots(coefficients: DoubleArray): List<Double> {
        val polynomial = PolynomialFunction(coefficients)
        val solver = PolynomialSolver()
        val initial = 0.0
        return solver.solveAllComplex(polynomial, initial).map { it.real }
    }
}

