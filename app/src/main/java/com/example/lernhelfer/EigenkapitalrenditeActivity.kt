package com.example.lernhelfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EigenkapitalrenditeActivity : AppCompatActivity() {
    private lateinit var enterGewinn: EditText
    private lateinit var enterEigenkapital: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tausenderpreis)

        enterGewinn = findViewById(R.id.editWerbepreis)
        enterEigenkapital = findViewById(R.id.editBruttoreichweite)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult = findViewById(R.id.textAusgabe)

        calculateButton.setOnClickListener {
            if (enterGewinn.text.toString().isNotEmpty() and enterEigenkapital.text.toString().isNotEmpty())  {
                val result = (enterGewinn.text.toString().toDouble() / enterEigenkapital.text.toString().toDouble()) * 100
                textViewResult.text = String.format("%.2f", result) + " €"
            } else {
                textViewResult.text = "Bitte überprüfen Sie ihre Eingabe"
            }

        }
    }
}

