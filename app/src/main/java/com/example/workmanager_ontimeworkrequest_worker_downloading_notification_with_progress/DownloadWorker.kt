package com.example.workmanager_ontimeworkrequest_worker_downloading_notification_with_progress

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DownloadWorker(c: Context, wp: WorkerParameters) : CoroutineWorker(c, wp) {

    var i = inputData.getInt("i", 1)

    override suspend fun doWork(): Result {

        try {
            var nm = NotificationManagerCompat.from(applicationContext)
            var notification = NotificationCompat.Builder(applicationContext, App.id)

                .setProgress(100, 0, false)
                .setOngoing(true)
                .setContentTitle("Download")
                .setContentText("Download Started")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(
                    PendingIntent.getActivity(
                        applicationContext, 1,
                        Intent(applicationContext, MainActivity::class.java), 0
                    )
                )
                .setColor(Color.GREEN)
                .setOnlyAlertOnce(true)

            nm.notify(1, notification.build())


            for (i in 0..20) {
                notification
                    .setProgress(100, i, false)
                    .setContentText("Downloading...")

                SystemClock.sleep(1000)

                nm.notify(1,notification.build())

            }

            notification
                .setProgress(0, 0, false)
                .setOngoing(false)
                .setContentText("Download Completed!!")

            nm.notify(1,notification.build())

            return Result.success()

        } catch (e: Exception) {
            return Result.failure()
        }


    }
}