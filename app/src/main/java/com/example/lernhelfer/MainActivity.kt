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
        setContentView(R.layout.activity_main)f

        val listView1: ListView = findViewById(R.id.listview1)
        val listView2: ListView = findViewById(R.id.listview2)
        val listView3: ListView = findViewById(R.id.listview3)

        val list1 = listOf("Bezugspreiskalkulation", "Deckungsbeitrag", "Preiselastizitaet", "Tausenderpreis", "Tausenderpreis(Qualitativ)", "Eigenkapitalrendite")
        val list2 = listOf("Dichte", "Stoffmenge")
        val list3 = listOf("Nullstellen")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list1)
        listView1.adapter = arrayAdapter

        val arrayAdapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, list2)
        listView2.adapter = arrayAdapter2

        val arrayAdapter3 = ArrayAdapter(this, android.R.layout.simple_list_item_1, list3)
        listView3.adapter = arrayAdapter3

        listView1.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this@MainActivity, BezugspreisActivity::class.java))
                1 -> startActivity(Intent(this@MainActivity, DeckungsbeitragActivity::class.java))
                2 -> startActivity(Intent(this@MainActivity, PreiselastizitaetActivity::class.java))
                3 -> startActivity(Intent(this@MainActivity, TausenderpreisActivity::class.java))
                4 -> startActivity(Intent(this@MainActivity, Tausenderpreis_Qualitativ_Activity::class.java))
                5 -> startActivity(Intent(this@MainActivity, EigenkapitalrenditeActivity::class.java))
                // Add more cases for other positions if needed
            }
        }

        listView2.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this@MainActivity, DichteActivity::class.java))
                1 -> startActivity(Intent(this@MainActivity, StoffmengeActivity::class.java))
                2 -> startActivity(Intent(this@MainActivity, NullstelleActivity::class.java))
                // Add more cases for other positions if needed
            }
        }

        listView3.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this@MainActivity, NullstelleActivity::class.java))
                // Add more cases for other positions if needed
            }
        }
    }
}
