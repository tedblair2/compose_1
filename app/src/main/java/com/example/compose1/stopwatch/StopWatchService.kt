package com.example.compose1.stopwatch

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.IBinder
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.compose1.R
import com.example.compose1.stopwatch.Constants.NOTIFICATION_ID
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.seconds

class StopWatchService : Service() {
    private val serviceBinder=ServiceBinder()

    private var duration= ZERO
    private lateinit var timer: Timer

    var seconds= mutableStateOf("00")
        private set
    var minutes= mutableStateOf("00")
        private set
    var hours= mutableStateOf("00")
        private set
    var currentState= mutableStateOf(StopWatchState.IDLE)
        private set

    private lateinit var notificationBuilder:NotificationCompat.Builder
    private lateinit var notificationManager: NotificationManagerCompat

    override fun onBind(intent: Intent): IBinder {
        return serviceBinder
    }

    override fun onCreate() {
        super.onCreate()
        notificationBuilder=NotificationCompat.Builder(baseContext,Constants.CHANNEL_ID)
            .setContentTitle("Stop Watch Counter")
            .setContentText("00:00:00")
            .setSmallIcon(R.drawable.baseline_timer_24)
            .setOngoing(true)
            .addAction(0,"Stop",ServiceHelper.stopPendingIntent(baseContext))
            .addAction(0,"Cancel",ServiceHelper.cancelPendingIntent(baseContext))
            .setContentIntent(ServiceHelper.clickPendingIntent(baseContext))
        notificationManager=NotificationManagerCompat.from(baseContext)
    }

    inner class ServiceBinder:Binder(){
        fun getService():StopWatchService=this@StopWatchService
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.getStringExtra(Constants.STOPWATCH_STATE)){
            StopWatchState.STARTED.name->{
                setStopButton()
                startForegroundService()
                startStopWatch{h, m, s ->
                    updateNotification(h,m,s)
                }
            }
            StopWatchState.STOPPED.name->{
                stopStopWatch()
                setResumeButton()
            }
            StopWatchState.CANCELED.name->{
                stopStopWatch()
                cancelStopWatch()
                stopForegroundService()
            }
        }
        intent?.action.let {
            when(it){
                Constants.ACTION_SERVICE_START->{
                    setStopButton()
                    startForegroundService()
                    startStopWatch{h, m, s ->
                        updateNotification(h,m,s)
                    }
                }
                Constants.ACTION_SERVICE_STOP->{
                    stopStopWatch()
                    setResumeButton()
                }
                Constants.ACTION_SERVICE_RESET->{
                    stopStopWatch()
                    cancelStopWatch()
                    stopForegroundService()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun updateNotification(h:String,m: String,s: String){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(
            NOTIFICATION_ID,
            notificationBuilder.setContentText(
                formatTime(s,m,h)
            ).build()
        )
    }
    private fun startStopWatch(onTick:(h:String,m:String,s:String)->Unit){
        currentState.value=StopWatchState.STARTED
        timer= fixedRateTimer(initialDelay = 1000L, period = 1000L){
            duration=duration.plus(1.seconds)
            updateTimeUnits()
            onTick(hours.value,minutes.value,seconds.value)
        }
    }

    private fun stopStopWatch(){
        if (this::timer.isInitialized){
            timer.cancel()
        }
        currentState.value=StopWatchState.STOPPED
    }

    private fun cancelStopWatch(){
        duration= ZERO
        currentState.value=StopWatchState.CANCELED
        updateTimeUnits()
    }

    private fun updateTimeUnits(){
        duration.toComponents { hours, minutes, seconds, _ ->
            this.hours.value=hours.toInt().pad()
            this.minutes.value=minutes.pad()
            this.seconds.value=seconds.pad()
        }
    }

    private fun startForegroundService(){
        startForeground(NOTIFICATION_ID,notificationBuilder.build())
    }
    private fun stopForegroundService(){
        notificationManager.cancel(NOTIFICATION_ID)
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    @SuppressLint("RestrictedApi")
    private fun setStopButton(){
        notificationBuilder.mActions.removeAt(0)
        notificationBuilder.mActions.add(
            0,
            NotificationCompat.Action(0,"Stop",ServiceHelper.stopPendingIntent(this))
        )
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(NOTIFICATION_ID,notificationBuilder.build())
    }

    @SuppressLint("RestrictedApi")
    private fun setResumeButton(){
        notificationBuilder.mActions.removeAt(0)
        notificationBuilder.mActions.add(
            0,
            NotificationCompat.Action(0,"Resume",ServiceHelper.resumePendingIntent(this))
        )
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(NOTIFICATION_ID,notificationBuilder.build())
    }

    private fun formatTime(s:String,m:String,h:String):String{
        return "$h:$m:$s"
    }

    private fun Int.pad():String{
        return this.toString().padStart(2,'0')
    }
}