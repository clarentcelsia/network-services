package com.example.network.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.*
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.network.util.Constant.Companion.isNetworkConnected

class NetworkRegister (
    context: Context
) {
    private val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var _isConnected: MutableLiveData<Boolean> = MutableLiveData()

    fun isConnected(): LiveData<Boolean>{
        startNetworkCallback()
        return _isConnected
    }

    /**
     *  Register Network Callback needs Network Request and Network Callback.
     *
     *  Base class for [NetworkRequest] callbacks.
     *  Used for notifications about network changes.
     *  Should be extended by [application] wanting notifications.
     *
     *  This [NetworkCallback] is registered by calling
     *
     *  Available for API 21 and above
     *  [ConnectivityManager.requestNetwork(NetworkRequest, ConnectivityManager.NetworkCallback)],
     *
     *  Available for API 21 and above
     *  [ConnectivityManager.registerNetworkCallback(NetworkRequest, ConnectivityManager.NetworkCallback)] or
     *
     *  Available for API 24 and above
     *  [ConnectivityManager.registerDefaultNetworkCallback(ConnectivityManager.NetworkCallback)]
     *
     *  @param [NetworkRequest] defines a request for network, made through [NetworkRequest.Builder()] to specify the [NetworkCapabilities] features.
     *  @param [NetworkCallback] that system will call as suitable networks change state.
     */
    fun startNetworkCallback(){
        // NET_CAPABILITY_INTERNET indicates this network should be able to reach the internet.
        val requestBuilder = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .addTransportType(TRANSPORT_WIFI)
            .addTransportType(TRANSPORT_ETHERNET)
            .addTransportType(TRANSPORT_CELLULAR)
            .build()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            connManager.registerDefaultNetworkCallback(networkCallback)
        }else {
            connManager.registerNetworkCallback(requestBuilder, networkCallback)
        }
    }

    fun stopNetworkCallback(){
        connManager.unregisterNetworkCallback(networkCallback)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback(){
        // Called when the framework connects and has declared a new network ready for use.
        override fun onAvailable(network: Network) {
            isNetworkConnected = true
            _isConnected.postValue(isNetworkConnected)
        }

        override fun onLost(network: Network) {
            isNetworkConnected = false
            _isConnected.postValue(isNetworkConnected)
        }
    }
}