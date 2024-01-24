package com.pushpak.servicecheking

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    val TAG = "MyService"
    override fun onBind(p0: Intent?): IBinder?  = null

    init {
        Log.d(TAG, "MyService is Running")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       var data = intent?.getStringExtra("EXTRA_DATA")
        data?.let {
            Log.d(TAG, "MyService data $data")
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "service being killed...")
    }
}