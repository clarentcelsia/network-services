package com.example.network.util

import android.util.Log
import kotlin.properties.Delegates

class Constant {

    companion object{
        // this variable will be observed if there's a change
        var isNetworkConnected: Boolean by Delegates.observable(false){ props, oldValue, newValue ->
            Log.i("constant","${newValue}")
        }
    }
}