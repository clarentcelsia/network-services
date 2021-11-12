package com.example.network.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.network.R
import com.example.network.service.NetworkRegister
import com.example.network.util.ConnectionLiveData
import com.example.network.util.Constant.Companion.isNetworkConnected
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkRegister(this).isConnected().observe(this){ connected->
            Toast.makeText(this, "isConnected: ${connected}", Toast.LENGTH_SHORT).show()
        }
    }

}