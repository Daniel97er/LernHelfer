package com.example.lernhelfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DeckungsbeitragActivity : AppCompatActivity() {
    private lateinit var enterBarverkaufspreis: EditText
    private lateinit var enterVariableKosten: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deckungsbeitrag)

        enterBarverkaufspreis = findViewById(R.id.editBarverkaufspreis)
        enterVariableKosten = findViewById(R.id.editVariableKosten)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult = findViewById(R.id.textAusgabe1)

        calculateButton.setOnClickListener {
            if (enterBarverkaufspreis.text.toString().isNotEmpty() and (enterVariableKosten.text.toString().isNotEmpty())) {
                    val deckungsbeitrag = enterBarverkaufspreis.text.toString().toDouble() - enterVariableKosten.text.toString().toDouble()
                    textViewResult.text = String.format("%.2f", deckungsbeitrag) + " €"
                } else {
                textViewResult.text = "Bitte überprüfen Sie ihre Eingaben"
            }

        }
    }

}