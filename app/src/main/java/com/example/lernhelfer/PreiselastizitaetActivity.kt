package com.example.lernhelfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PreiselastizitaetActivity : AppCompatActivity() {
    private lateinit var enterNeueMenge: EditText
    private lateinit var enterAlteMenge: EditText
    private lateinit var enterNeuerPreis: EditText
    private lateinit var enterAlterPreis: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preiselastizitaet)

        enterNeueMenge = findViewById(R.id.editNeueMenge)
        enterAlteMenge = findViewById(R.id.editAlteMenge)
        enterNeuerPreis = findViewById(R.id.editNeuerPreis)
        enterAlterPreis = findViewById(R.id.editAlterPreis)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult = findViewById(R.id.textAusgabe)

        calculateButton.setOnClickListener {
            if (enterNeueMenge.text.toString().isNotEmpty() and enterAlteMenge.text.toString().isNotEmpty() and enterNeuerPreis.text.toString().isNotEmpty() and enterAlterPreis.text.toString().isNotEmpty()) {
                var preiselastizitaet = ((enterNeueMenge.text.toString().toDouble() - enterAlteMenge.text.toString().toDouble()) / enterAlteMenge.text.toString().toDouble()) / ((enterNeuerPreis.text.toString().toDouble() - enterAlterPreis.text.toString().toDouble()) / enterAlterPreis.text.toString().toDouble())
                preiselastizitaet *= -1
                textViewResult.text = String.format("%.2f", preiselastizitaet) + " €"
            } else {
                textViewResult.text = "Bitte überprüfen Sie ihre Eingaben"
            }

        }
    }
}