package com.example.lernhelfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StoffmengeActivity : AppCompatActivity() {
    private lateinit var enterMasse: EditText
    private lateinit var enterMolmasse: EditText
    private lateinit var calculateButton: Button
    private lateinit var textViewResult: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stoffmenge)

        enterMasse = findViewById(R.id.editMasse)
        enterMolmasse = findViewById(R.id.editMolmasse)
        calculateButton = findViewById(R.id.buttonBerechnen)
        textViewResult = findViewById(R.id.textAusgabe)

        calculateButton.setOnClickListener {
            if (enterMasse.text.toString().isNotEmpty() and enterMolmasse.text.toString().isNotEmpty()) {
                val result =
                    textViewResult.text = String.format("%.2f", result)
            } else {
                textViewResult.text = "Bitte überprüfen Sie ihre Eingaben"
            }

        }
    }
}