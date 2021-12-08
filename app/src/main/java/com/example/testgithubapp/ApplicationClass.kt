package com.example.testgithubapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log

class ApplicationClass : Application() {

    companion object {
        // set application context
        lateinit var appContext: Context

        // Get or Set foreground activity
        @SuppressLint("StaticFieldLeak")
        var mCurrentActivity: Activity? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        // Internet checking
//        NetworkXCore.init(this, NetworkXObservingStrategy.HIGH)
    }

    override fun onTerminate() {
        Log.d("TAG", "onTerminate: CenturyTerminated")
        mCurrentActivity = null
        super.onTerminate()

    }
}