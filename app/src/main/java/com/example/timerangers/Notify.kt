package com.example.timerangers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.widget.Button
import com.example.timerangers.databinding.ActivityNotifyBinding
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.graphics.Color
import android.os.Build



class Notify : AppCompatActivity() {



    lateinit var binding: ActivityNotifyBinding



    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIF_ID = 0



    private lateinit var btnSaveData: Button
    private lateinit var navBtn: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifyBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val buttonClick = findViewById<Button>(R.id.progress)
        buttonClick.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
        createNotifChannel()



        val intent=Intent(this, Notify::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }



        val notif = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Reminder- remember to complete task")
            .setContentText("Complete today's tasks and earn reward points to meet your daily goals")
            .setSmallIcon(R.drawable.ic_baseline_info_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()



        val notifManger = NotificationManagerCompat.from(this)



        binding.btnShowNotif.setOnClickListener {
            notifManger.notify(NOTIF_ID,notif)
        }



    }



    private fun createNotifChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}