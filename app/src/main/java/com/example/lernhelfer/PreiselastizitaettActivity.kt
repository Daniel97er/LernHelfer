package com.example.lernhelfer

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.zip


class PreiselastizitaettActivity : AppCompatActivity() {
    private lateinit var enterNeueMenge: EditText
    private lateinit var enterAlteMenge: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult: TextView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preiselastizitaett)

        enterNeueMenge = findViewById(R.id.editNeueMenge)
        enterAlteMenge = findViewById(R.id.editAlteMenge)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult = findViewById(R.id.textAusgabe)

        calculateButton.setOnClickListener {
            if (enterNeueMenge.text.toString().isNotEmpty() and enterAlteMenge.text.toString().isNotEmpty()) {
                data class Element(val symbol: String, val count: Int)
                data class Compound(val formula: String, val elements: List<Element>)

                fun parseCompound(compound: String): Compound {
                    val elementRegex = Regex("([A-Z][a-z]*)(\\d*)")
                    val elements = elementRegex.findAll(compound).map {
                        val symbol = it.groupValues[1]
                        val count = it.groupValues[2].toIntOrNull() ?: 1
                        Element(symbol, count)
                    }.toList()
                    return Compound(compound, elements)
                }

                fun generatePossibleProducts(reactants: List<Compound>): List<Compound> {
                    // Einfache Generierung von möglichen Produkten, z.B. Kombination von Reaktanten
                    val products = mutableListOf<Compound>()
                    // Beispiel: Kombiniere Reaktanten zu möglichen Produkten (sehr vereinfachter Ansatz)
                    for (i in reactants.indices) {
                        for (j in i + 1 until reactants.size) {
                            val combinedFormula = reactants[i].formula + reactants[j].formula
                            val combinedElements = reactants[i].elements + reactants[j].elements
                            products.add(Compound(combinedFormula, combinedElements))
                        }
                    }
                    return products
                }

                fun buildMatrix(reactants: List<Compound>, products: List<Compound>): Array<DoubleArray> {
                    val allElements = (reactants + products).flatMap { it.elements.map { element -> element.symbol } }.distinct()
                    val matrixData = Array(allElements.size) { DoubleArray(reactants.size + products.size) }

                    for (i in allElements.indices) {
                        val element = allElements[i]
                        reactants.forEachIndexed { j, compound ->
                            matrixData[i][j] = compound.elements.find { it.symbol == element }?.count?.toDouble() ?: 0.0
                        }
                        products.forEachIndexed { j, compound ->
                            matrixData[i][reactants.size + j] = -(compound.elements.find { it.symbol == element }?.count?.toDouble() ?: 0.0)
                        }
                    }

                    return matrixData
                }

                fun gaussJordanElimination(matrix: Array<DoubleArray>): DoubleArray {
                    val n = matrix.size
                    val m = matrix[0].size
                    val augmentedMatrix = matrix.map { it.copyOf(m + 1) }.toTypedArray()

                    for (i in 0 until n) {
                        augmentedMatrix[i][m] = 1.0
                    }

                    for (i in 0 until n) {
                        var maxRow = i
                        for (k in i + 1 until n) {
                            if (Math.abs(augmentedMatrix[k][i]) > Math.abs(augmentedMatrix[maxRow][i])) {
                                maxRow = k
                            }
                        }

                        val temp = augmentedMatrix[i]
                        augmentedMatrix[i] = augmentedMatrix[maxRow]
                        augmentedMatrix[maxRow] = temp

                        for (k in i + 1 until n) {
                            val factor = augmentedMatrix[k][i] / augmentedMatrix[i][i]
                            for (j in i until m + 1) {
                                augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j]
                            }
                        }
                    }

                    val solution = DoubleArray(m)
                    for (i in n - 1 downTo 0) {
                        solution[i] = augmentedMatrix[i][m] / augmentedMatrix[i][i]
                        for (k in 0 until i) {
                            augmentedMatrix[k][m] -= augmentedMatrix[k][i] * solution[i]
                        }
                    }

                    return solution
                }

                fun balanceEquation(reactants: List<String>): String {
                    val reactantCompounds = reactants.map { parseCompound(it) }
                    val possibleProducts = generatePossibleProducts(reactantCompounds)

                    var balancedEquation = "Keine Lösung gefunden"

                    for (products in possibleProducts) {
                        val productCompounds = listOf(products)
                        val matrix = buildMatrix(reactantCompounds, productCompounds)
                        val solution = gaussJordanElimination(matrix)

                        if (solution.all { it >= 0 }) {
                            balancedEquation = reactants.zip(solution.toList()).joinToString(" + ") { "${it.second.toInt()}${it.first}" } +
                                    " -> " + productCompounds.zip(solution.drop(reactants.size)).joinToString(" + ") { "${it.second.toInt()}${it.first.formula}" }
                            break
                        }
                    }

                    return balancedEquation
                }
                val reactants = listOf(enterNeueMenge.text.toString(), enterAlteMenge.text.toString())
                val balancedEquation = balanceEquation(reactants)
                    //textViewResult.text = String.format("%.2f", preiselastizitaet)
                textViewResult.text = ("Balancierte Reaktionsgleichung: $balancedEquation")
            } else {
                textViewResult.text = "Bitte überprüfen Sie ihre Eingaben"
            }

        }
    }
}