package com.example.network

import android.app.Application
import android.util.Log
import com.example.network.service.NetworkRegister
import timber.log.Timber

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
//        NetworkRegister(this).startNetworkCallback()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}