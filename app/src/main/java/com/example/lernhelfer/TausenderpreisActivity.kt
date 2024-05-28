package com.example.lernhelfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TausenderpreisActivity : AppCompatActivity() {
    private val inchValue = 2.54
    private lateinit var enterWerbepreis: EditText
    private lateinit var enterBruttoreichweite: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tausenderpreis)

        enterWerbepreis = findViewById(R.id.editWerbepreis)
        enterBruttoreichweite = findViewById(R.id.editBruttoreichweite)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult = findViewById(R.id.textAusgabe)

        calculateButton.setOnClickListener {
            if (enterWerbepreis.text.toString().isNotEmpty() and enterBruttoreichweite.text.toString().isNotEmpty())  {
                val result = (enterWerbepreis.text.toString().toDouble() / enterBruttoreichweite.text.toString().toDouble()) * 1000
                textViewResult.text = String.format("%.2f", result) + " €"
            } else {
                textViewResult.text = "Bitte überprüfen Sie ihre Eingabe"
        }

        }
    }
}