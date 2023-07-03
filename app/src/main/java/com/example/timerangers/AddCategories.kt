package com.example.timerangers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddCategories : AppCompatActivity() {

    private lateinit var category: EditText
    private lateinit var activityName: EditText
    private lateinit var imageUrl: EditText
    private lateinit var description: EditText
    private lateinit var startDate: EditText
    private lateinit var endDate: EditText
    private lateinit var btnSaveData: Button

    //connecting database
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_categories)

        category = findViewById(R.id.category)
        activityName = findViewById(R.id.activityName)
        imageUrl = findViewById(R.id.imageUrl)
        description = findViewById(R.id.description)
        startDate = findViewById(R.id.startDate)
        endDate = findViewById(R.id.endDate)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Categories")

        btnSaveData.setOnClickListener {
            saveCategoryData()
        }
    }

private fun saveCategoryData() {


    // null checking
    if (category.text.isEmpty() || activityName.text.isEmpty() || imageUrl.text.isEmpty() ||
            description.text.isEmpty() || startDate.text.isEmpty() || endDate.text.isEmpty()){
        Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT)
            .show()
        return
    }
    //getting values
    val category = category.text.toString()
    val activityName = activityName.text.toString()
    val imageUrl = imageUrl.text.toString()
    val description = description.text.toString()
    val startDate = startDate.text.toString()
    val endDate = endDate.text.toString()

    //creating a primary key
    val categoryID = dbRef.push().key!!

    val categoryModel = CategoryModel (categoryID, category, activityName, imageUrl, description, startDate, endDate)

    dbRef.child(categoryID).setValue(categoryModel)
        .addOnCompleteListener {
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()
            //  navigate to next
            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
        }

}

}