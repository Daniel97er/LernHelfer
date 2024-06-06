package com.example.lernhelfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BezugspreisActivity : AppCompatActivity() {
    private lateinit var enterListeneinkaufspreis: EditText
    private lateinit var enterLieferantenrabatt: EditText
    private lateinit var enterLieferantenskonti: EditText
    private lateinit var enterBezugskosten: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult1: TextView
    private lateinit var textViewResult2: TextView
    private lateinit var textViewResult3: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezugspreis)

        enterListeneinkaufspreis = findViewById(R.id.editListeneinkaufspreis)
        enterLieferantenrabatt = findViewById(R.id.editLieferantenrabatt)
        enterLieferantenskonti = findViewById(R.id.editLieferantenskonti)
        enterBezugskosten = findViewById(R.id.editBezugskosten)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult1 = findViewById(R.id.textAusgabe1)
        textViewResult2 = findViewById(R.id.textAusgabe2)
        textViewResult3 = findViewById(R.id.textAusgabe3)

        calculateButton.setOnClickListener {
            if (enterListeneinkaufspreis.text.toString().isNotEmpty() and enterLieferantenrabatt.text.toString().isNotEmpty() and enterLieferantenskonti.text.toString().isNotEmpty() and enterBezugskosten.text.toString().isNotEmpty()) {
                    val zieleinkaufspreis = enterListeneinkaufspreis - ((enterListeneinkaufspreis.text.toString().toDouble() / 100) * enterLieferantenrabatt)
                    val bareinkaufspreis = zieleinkaufspreis - ((zieleinkaufspreis / 100) * enterLieferantenskonti)
                    val bezugspreis = bareinkaufspreis + enterBezugskosten
                    textViewResult1.text = String.format("%.2f", zieleinkaufspreis) + " €"
                    textViewResult2.text = String.format("%.2f", bareinkaufspreis) + " €"
                    textViewResult3.text = String.format("%.2f", bezugspreis) + " €"
                } else {
                textViewResult1.text = "Bitte überprüfen Sie ihre Eingaben"
            }

        }
    }
}