package com.example.timerangers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FetchCategories : AppCompatActivity() {
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var categoryList: ArrayList<CategoryModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_categories)

        categoryRecyclerView = findViewById(R.id.rvCategory)
        categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        categoryRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        categoryList = arrayListOf<CategoryModel>()

        getCategoryData()

    }

    private fun getCategoryData() {

        categoryRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Categories")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                categoryList.clear()
                if (snapshot.exists()){
                    for (categorySnap in snapshot.children){
                        val categoryData = categorySnap.getValue(CategoryModel::class.java)
                        categoryList.add(categoryData!!)
                    }
                    val mAdapter = CategoryAdapter(categoryList)
                    categoryRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : CategoryAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchCategories, CategoryDetials::class.java)

                            //put extras
                            intent.putExtra("categoryID", categoryList[position].categoryID)
                            intent.putExtra("category", categoryList[position].category)
                            intent.putExtra("activityName", categoryList[position].activityName)
                            intent.putExtra("imageUrl", categoryList[position].imageUrl)
                            intent.putExtra("description", categoryList[position].description)
                            intent.putExtra("startDate", categoryList[position].startDate)
                            intent.putExtra("endDate", categoryList[position].endDate)
                            startActivity(intent)
                        }

                    })

                    categoryRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}