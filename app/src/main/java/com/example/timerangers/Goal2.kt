package com.example.timerangers

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

class Goal2 : AppCompatActivity() {

    lateinit var barChart: BarChart
    private lateinit var navBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal2)

        barChart=findViewById(R.id.bar_chart)



        navBtn = findViewById<Button>(R.id.menuButton)
        navBtn.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
        //next screen
        val buttonClick = findViewById<Button>(R.id.progress)
        buttonClick.setOnClickListener {
            val intent = Intent(this, Goal::class.java)
            startActivity(intent)
        }

        barChart=findViewById(R.id.bar_chart)


        val list:ArrayList<BarEntry> =ArrayList()

        list.add(BarEntry(22f,22f))
        list.add(BarEntry(40f,40f))
        list.add(BarEntry(10f,10f))

        val barDataSet= BarDataSet(list,
            "Total Hours: GREEN     Maximum Goal: YELLOW    Minimum Goal: RED")

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)

        barDataSet.valueTextColor= Color.BLACK

        val barData= BarData(barDataSet)

        barChart.setFitBars(true)

        barChart.data=barData

        barChart.description.text="Hours"

        barChart.animateY(2000)

    }
}