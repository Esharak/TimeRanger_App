package com.example.timerangers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.timerangers.databinding.ActivityProgressBinding

class Progress : AppCompatActivity() {

    lateinit var binding: ActivityProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var increment_number=0;

        binding.buttonIncrement.setOnClickListener {
            increment_number++
            binding.incrementTextview.text=increment_number.toString()
        }

       // val btnRegister: Button = findViewById(R.id.button_increment)
       // btnRegister.setOnClickListener {
       //     Toast.makeText(this, "Task has been completed", Toast.LENGTH_SHORT).show()
       // }

        val buttonClick = findViewById<Button>(R.id.button2)
        buttonClick.setOnClickListener {
            val intent = Intent(this, Roll::class.java)
            startActivity(intent)
        }
    }
}