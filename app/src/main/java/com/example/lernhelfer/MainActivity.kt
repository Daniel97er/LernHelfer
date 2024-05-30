package com.example.lernhelfer

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listview)

        val list = listOf("Tausenderpreis", "Orange", "Banana", "Grapes")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = arrayAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this@MainActivity, TausenderpreisActivity::class.java))
                1 -> startActivity(Intent(this@MainActivity, OrangeActivity::class.java))
                // Add more cases for other positions if neededdd
            }
        }
    }
}
