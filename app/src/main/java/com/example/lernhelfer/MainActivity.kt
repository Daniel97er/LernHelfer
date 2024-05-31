package com.example.lernhelfer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView1: ListView = findViewById(R.id.listview1)
        val listView2: ListView = findViewById(R.id.listview2)

        val list1 = listOf("Tausenderpreis", "Orange", "Banana", "Grapes")
        val list2 = listOf("anne", "paul", "lars")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list1)
        listView1.adapter = arrayAdapter

        val arrayAdapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, list2)
        listView2.adapter = arrayAdapter2

        listView1.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this@MainActivity, TausenderpreisActivity::class.java))
                1 -> startActivity(Intent(this@MainActivity, OrangeActivity::class.java))
                // Add more cases for other positions if needed
            }
        }

        listView2.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this@MainActivity, TausenderpreisActivity::class.java))
                1 -> startActivity(Intent(this@MainActivity, OrangeActivity::class.java))
                // Add more cases for other positions if needed
            }
        }
    }
}
