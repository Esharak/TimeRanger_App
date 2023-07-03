package com.example.timerangers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val registertext: TextView = findViewById(R.id.textView_register)

        registertext.setOnClickListener{
            val registerintent = Intent( this, Register::class.java)
            startActivity(registerintent)
        }

        val loginButton: Button = findViewById(R.id.btnLogin)

        loginButton.setOnClickListener {
            perfromLogin()
        }
    }

    private fun perfromLogin() {
        // getting input
        val email: EditText = findViewById(R.id.emailTxtLogin)
        val password: EditText = findViewById(R.id.passwordTxtLogin)

        // null checking
        if (email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this, "Please fill al the fields", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val emailInput = email.text.toString()
        val passwordInput = password.text.toString()

        auth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, navigate to next
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                    Toast.makeText(baseContext, "Authentication Successful.", Toast.LENGTH_SHORT,).show()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT,).show()

                }
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Authentication Failed. ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}