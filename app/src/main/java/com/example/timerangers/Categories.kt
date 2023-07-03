package com.example.timerangers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Categories : AppCompatActivity() {

    private lateinit var btnInsertData: Button
    private lateinit var btnFetchData: Button
    private lateinit var navBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        btnInsertData = findViewById(R.id.btnInsertData)
        btnFetchData = findViewById(R.id.btnFetchData)
        navBtn = findViewById<Button>(R.id.menuButton)

        btnInsertData.setOnClickListener{
            val intent = Intent(this, AddCategories::class.java)
            startActivity(intent)
        }
        btnFetchData.setOnClickListener{
           val intent = Intent(this, FetchCategories::class.java)
           startActivity(intent)
        }
        navBtn.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }
}