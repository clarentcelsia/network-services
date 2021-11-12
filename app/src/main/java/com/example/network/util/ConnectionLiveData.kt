package com.example.network.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.network.service.NetworkRegister
import com.example.network.util.Constant.Companion.isNetworkConnected

// To observe network connection continuously, you can use live data / broadcast receiver
// https://protocoderspoint.com/how-to-check-internet-connection-in-android/
// https://github.com/mitchtabian/food2fork-compose/blob/master/app/src/main/java/com/codingwithmitch/food2forkcompose/presentation/util/ConnectionLiveData.kt
class ConnectionLiveData(private val context: Context): LiveData<Boolean>() {

    var _isConnected: MutableLiveData<Boolean> = MutableLiveData()

    override fun onActive() {
        NetworkRegister(context).startNetworkCallback()
        updateConn()
    }

    override fun onInactive() {
        NetworkRegister(context).stopNetworkCallback()
    }

    private fun updateConn(){
        _isConnected.value = isNetworkConnected
    }
}
