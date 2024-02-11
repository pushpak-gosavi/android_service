package com.pushpak.servicecheking

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.pushpak.servicecheking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val airplaneBoradcastreciver = AirplaneBoradcastreciver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartService.setOnClickListener {
            /**
             * Intent Service program
             */
//        Intent(this, MyIntentService::class.java).also {
//            startService(it)
//        }

            /**
             * Service Program
             */

            Intent(this, MyService::class.java).also {
                startService(it)
            }

            binding.tvText.text = "Service is running..."
        }
        binding.btnStopService.setOnClickListener {
            //MyIntentService.stopService()
            Intent(this, MyService::class.java).also {
                Log.d("MYService", "Service is stopping...")
                stopService(it)
            }
            binding.tvText.text = "Service is Stop"
        }

        binding.btnSendData.setOnClickListener {
            val data = binding.edtData.text.toString()
            Intent(this, MyService::class.java).also {
                it.putExtra("EXTRA_DATA", data)
                startService(it)
            }
        }

        /**
         * Broadcast Receiver register
         */

        registerReceiver(
            airplaneBoradcastreciver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airplaneBoradcastreciver)
    }
}