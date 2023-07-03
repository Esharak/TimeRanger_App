package com.example.timerangers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val loginText: TextView = findViewById(R.id.textView_login)
        loginText.setOnClickListener {
            val loginIntent = Intent(this, Login::class.java)
            startActivity(loginIntent)
        }


        val btnRegister: Button = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            preformSignUp()
        }
    }

    private fun preformSignUp() {
        val email = findViewById<EditText>(R.id.emailTxtRegister)
        val password = findViewById<EditText>(R.id.passwordTxtRegister)

        if(email.text.isEmpty() && password.text.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, move next activity

                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Authentication Successful.", Toast.LENGTH_SHORT,).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT,).show()

                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "Error ocurred ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}