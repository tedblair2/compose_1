package com.example.compose1.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.compose1.di.appModule
import com.example.compose1.stopwatch.Constants.CHANNEL_ID
import com.example.compose1.stopwatch.Constants.CHANNEL_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppAppliation:Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startKoin{
            androidLogger()
            androidContext(this@AppAppliation)
            modules(appModule)
        }
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel=NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_LOW)
            val manager=getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}