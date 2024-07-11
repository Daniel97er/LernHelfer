package com.example.lernhelfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DichteActivity : AppCompatActivity() {
    private lateinit var enterMasse: EditText
    private lateinit var enterVolumen: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dichte)

        enterMasse = findViewById(R.id.editMasse)
        enterVolumen = findViewById(R.id.editVolumen)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult = findViewById(R.id.textAusgabe)

        calculateButton.setOnClickListener {
            if (enterMasse.text.toString().isNotEmpty() and enterVolumen.text.toString().isNotEmpty()) {
                val result = (enterMasse.text.toString().toDouble() / enterVolumen.text.toString().toDouble())
                    textViewResult.text = String.format("%.2f", result)
            } else {
                textViewResult.text = "Bitte überprüfen Sie ihre Eingaben"
            }

        }
    }
}