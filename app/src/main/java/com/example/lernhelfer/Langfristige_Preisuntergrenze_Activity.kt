package com.example.lernhelfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Langfristige_Preisuntergrenze_Activity : AppCompatActivity() {
    private lateinit var enterFixkosten: EditText
    private lateinit var enterProduktionsmenge: EditText
    private lateinit var enterVariableKosten: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_langfristige_preisuntergrenze)

        enterFixkosten = findViewById(R.id.editFixkosten)
        enterProduktionsmenge = findViewById(R.id.editProduktionsmenge)
        enterVariableKosten = findViewById(R.id.editVariableKosten)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult = findViewById(R.id.textAusgabe)

        calculateButton.setOnClickListener {
            if (enterFixkosten.text.toString().isNotEmpty() and enterProduktionsmenge.text.toString().isNotEmpty() and enterVariableKosten.text.toString().isNotEmpty()) {
                var Langfristige_Preisuntergrenze = ((enterFixkosten.text.toString().toDouble() / enterProduktionsmenge.text.toString().toDouble()) + enterVariableKosten.text.toString().toDouble())
                textViewResult.text = String.format("%.2f", Langfristige_Preisuntergrenze) + " €"
            } else {
                textViewResult.text = "Bitte überprüfen Sie ihre Eingaben"
            }

        }
    }
}
