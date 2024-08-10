package com.example.lernhelfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class OptimaleBestellmengeActivity : AppCompatActivity() {
    private lateinit var enterBestellfixeKosten: EditText
    private lateinit var enterJahresverbrauchsmenge: EditText
    private lateinit var enterBezugspreis: EditText
    private lateinit var enterLagerkostensatz: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_optimale_bestellmenge)

        enterBestellfixeKosten = findViewById(R.id.editBestellfixeKosten)
        enterJahresverbrauchsmenge = findViewById(R.id.editJahresverbrauchsmenge)
        enterBezugspreis = findViewById(R.id.editBezugspreis)
        enterLagerkostensatz = findViewById(R.id.editLagerkostensatz)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult = findViewById(R.id.textAusgabe)

        calculateButton.setOnClickListener {
            if (enterBestellfixeKosten.text.toString().isNotEmpty() and enterJahresverbrauchsmenge.text.toString().isNotEmpty() and enterBezugspreis.text.toString().isNotEmpty() and enterLagerkostensatz.text.toString().isNotEmpty()) {
                var Optimale_Bestellmenge = sqrt((200 * enterBestellfixeKosten.text.toString().toDouble() * enterJahresverbrauchsmenge.text.toString().toDouble()) / (enterBezugspreis.text.toString().toDouble() * enterLagerkostensatz.text.toString().toDouble()))
                textViewResult.text = String.format("%.2f", Optimale_Bestellmenge) + " Stück"
            } else {
                textViewResult.text = "Bitte überprüfen Sie ihre Eingaben"
            }

        }
    }
}
