package com.pushpak.servicecheking

import android.app.IntentService
import android.content.Intent
import android.util.Log
import kotlin.math.log

class MyIntentService : IntentService("MyIntentService") {

    init {
        instance = this
    }
    companion object{
        lateinit var instance : MyIntentService
        var isRunning= false

        fun stopService() {
            isRunning = false
            Log.d("MyIntentService", "Intent Service is stopping...")
            instance.stopSelf()
        }
    }
    override fun onHandleIntent(p0: Intent?) {
        try {
            isRunning = true
            while (isRunning){
                Log.d("MyIntentService", "Intent Service is Running...")
                Thread.sleep(1000)
            }
        }catch (e:InterruptedException){
            Thread.currentThread().interrupt()
        }

    }
}