package com.example.testgithubapp.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.testgithubapp.ApplicationClass

object Internet {

    fun isNetworkConnected(): Boolean {
        val connectivityMgr = ApplicationClass.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityMgr.getNetworkCapabilities(connectivityMgr.activeNetwork)!=null
    }

}