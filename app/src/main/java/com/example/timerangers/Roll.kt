package com.example.timerangers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Roll : AppCompatActivity() {
    private lateinit var navBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roll)

        navBtn = findViewById<Button>(R.id.menuButton)
        navBtn.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        // access the items of the list
        val languages = resources.getStringArray(R.array.Languages)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }

        // Roll a dice when the app launches. This is because otherwise it would be an empty page.
        rollDice()
       // val btnRegister: Button = findViewById(R.id.button)
       // btnRegister.setOnClickListener {
        //    Toast.makeText(this, "You have received 10 points", Toast.LENGTH_SHORT).show()
       // }
    }

    //Roll a dice and set the drawable image to the view
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceImage: ImageView = findViewById(R.id.imageView)

        val drawableResource = when (diceRoll){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceRoll.toString()

    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}