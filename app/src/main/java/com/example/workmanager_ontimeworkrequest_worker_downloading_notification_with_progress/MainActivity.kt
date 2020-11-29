package com.example.workmanager_ontimeworkrequest_worker_downloading_notification_with_progress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var i = 0
    var workmanager:WorkManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workmanager = WorkManager.getInstance(applicationContext)

        bt_download.setOnClickListener {
            i++
            var owr = OneTimeWorkRequest
                .Builder(DownloadWorker::class.java)
                .setInputData(Data.Builder().putInt("i", i).build())
                .setConstraints(
                    Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                )
                .build()

            WorkManager.getInstance(this).enqueue(owr)

            WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(owr.id)
                .observe(this, {
                   tv_state.text = it.state.toString()
                })

        }
    }
}