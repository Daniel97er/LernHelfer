package com.example.lernhelfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import kotlin.math.*

class NullstelleActivity : AppCompatActivity() {
    private lateinit var a: EditText
    private lateinit var b: EditText
    private lateinit var c: EditText
    private lateinit var d: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult1: TextView
    private lateinit var textViewResult2: TextView
    private lateinit var textViewResult3: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nullstelle)

        a = findViewById(R.id.editA)
        b = findViewById(R.id.editB)
        c = findViewById(R.id.editC)
        d = findViewById(R.id.editD)

        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult1 = findViewById(R.id.textAusgabe1)
        textViewResult2 = findViewById(R.id.textAusgabe2)
        textViewResult3 = findViewById(R.id.textAusgabe3)

        calculateButton.setOnClickListener {
            if (a.text.toString().isNotEmpty() and b.text.toString().isNotEmpty() and c.text.toString().isNotEmpty() and d.text.toString().isNotEmpty()) {
                fun solveCubic(a: Double, b: Double, c: Double, d: Double): List<Double> {
                    // Umwandlung in die reduzierte kubische Gleichung t^3 + pt + q = 0
                    val f = ((3.0 * c / a) - ((b * b) / (a * a))) / 3.0
                    val g = ((2.0 * b * b * b) / (a * a * a) - (9.0 * b * c) / (a * a) + (27.0 * d / a)) / 27.0
                    val h = (g * g / 4.0) + (f * f * f / 27.0)

                    return when {
                        h > 0 -> {
                            // Eine reelle Wurzel
                            val r = -(g / 2.0) + sqrt(h)
                            val s = cbrt(r)
                            val t = -(g / 2.0) - sqrt(h)
                            val u = cbrt(t)
                            listOf((s + u) - (b / (3.0 * a)))
                        }
                        h == 0.0 && f == 0.0 && g == 0.0 -> {
                            // Alle drei Wurzeln sind gleich
                            listOf(-cbrt(d / a))
                        }
                        else -> {
                            // Drei reelle Wurzeln
                            val i = sqrt((g * g / 4.0) - h)
                            val j = cbrt(i)
                            val k = acos(-(g / (2 * i)))
                            val l = -j
                            val m = cos(k / 3.0)
                            val n = sqrt(3.0) * sin(k / 3.0)
                            val p = -(b / (3.0 * a))

                            listOf(
                                2 * j * cos(k / 3.0) - (b / (3 * a)),
                                l * (m + n) + p,
                                l * (m - n) + p
                            )
                        }
                    }
                }

                fun cbrt(x: Double): Double {
                    return if (x < 0) -(-x).pow(1.0 / 3.0) else x.pow(1.0 / 3.0)
                }
                val roots = solveCubic(a.text.toString().toDouble(), b.text.toString().toDouble(), c.text.toString().toDouble(), d.text.toString().toDouble())

                textViewResult1.text = if (roots.isNotEmpty()) String.format("%.2f", roots[0]) else ""
                textViewResult2.text = if (roots.size > 1) String.format("%.2f", roots[1]) else ""
                textViewResult3.text = if (roots.size > 2) String.format("%.2f", roots[2]) else ""
            } else {
                textViewResult1.text = "Bitte überprüfen Sie ihre Eingaben"
                textViewResult2.text = ""
                textViewResult3.text = ""
            }

        }
    }
}