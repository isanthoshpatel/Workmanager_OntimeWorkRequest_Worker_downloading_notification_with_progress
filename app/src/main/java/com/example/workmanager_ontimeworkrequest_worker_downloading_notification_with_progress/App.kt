package com.example.workmanager_ontimeworkrequest_worker_downloading_notification_with_progress

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class App:Application() {

    companion object{
        var id = "id"
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            var nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            var nch = NotificationChannel("id","nch1",NotificationManager.IMPORTANCE_HIGH)

            nm.createNotificationChannel(nch)

        }

    }
}