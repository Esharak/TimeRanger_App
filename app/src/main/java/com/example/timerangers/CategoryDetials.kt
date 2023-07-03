package com.example.timerangers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class CategoryDetials : AppCompatActivity() {

    private lateinit var categoryID: TextView
    private lateinit var category: TextView
    private lateinit var activityName: TextView
    private lateinit var imageUrl: TextView
    private lateinit var description: TextView
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var navBtn: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detials)

        navBtn = findViewById<Button>(R.id.menuButton)
        navBtn.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        initView()
        setValuesToViews()

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("categoryID").toString()
            )
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Categories").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Category data deleted", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, FetchCategories::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error ->
            Toast.makeText(this, "Deleting Error ${error.message} ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        categoryID = findViewById(R.id.categoryID)
        category = findViewById(R.id.category)
        activityName = findViewById(R.id.activityName)
        imageUrl = findViewById(R.id.imageUrl)
        description = findViewById(R.id.description)
        startDate = findViewById(R.id.startDate)
        endDate = findViewById(R.id.endDate)

        //btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }
    private fun setValuesToViews() {
        categoryID.text = intent.getStringExtra("categoryID")
        category.text = intent.getStringExtra("category")
        activityName.text = intent.getStringExtra("activityName")
        imageUrl.text = intent.getStringExtra("imageUrl")
        description.text = intent.getStringExtra("description")
        startDate.text = intent.getStringExtra("startDate")
        endDate.text = intent.getStringExtra("endDate")

    }
}