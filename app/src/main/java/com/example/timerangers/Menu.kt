package com.example.timerangers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val NavTimer = findViewById<Button>(R.id.timer)
        NavTimer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val NavCat = findViewById<Button>(R.id.categories)
        NavCat.setOnClickListener {
            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }

        val NavGoals = findViewById<Button>(R.id.Goals)
        NavGoals.setOnClickListener {
            val intent = Intent(this, Goal::class.java)
           startActivity(intent)
        }

        val Navprog = findViewById<Button>(R.id.progress)
         Navprog.setOnClickListener {
            val intent = Intent(this, Progress::class.java)
            startActivity(intent)
         }

        //val NavNotify = findViewById<Button>(R.id.notify)
        //NavNotify.setOnClickListener {
        //    val intent = Intent(this, Notify::class.java)
        //    startActivity(intent)
        //}

        val NavSettings = findViewById<Button>(R.id.settings)
        NavSettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
    }
}