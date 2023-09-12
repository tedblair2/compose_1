package com.example.compose1.stopwatch

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.compose1.MainActivity
import com.example.compose1.stopwatch.Constants.CANCEL_REQUEST_CODE
import com.example.compose1.stopwatch.Constants.CLICK_REQUEST_CODE
import com.example.compose1.stopwatch.Constants.RESUME_REQUEST_CODE
import com.example.compose1.stopwatch.Constants.STOPWATCH_STATE
import com.example.compose1.stopwatch.Constants.STOP_REQUEST_CODE

object ServiceHelper {

    private const val flag= PendingIntent.FLAG_IMMUTABLE

    fun clickPendingIntent(context: Context):PendingIntent{
        val clickIntent=Intent(context,MainActivity::class.java)
        return PendingIntent.getActivity(context,CLICK_REQUEST_CODE,clickIntent, flag)
    }

    fun stopPendingIntent(context: Context):PendingIntent{
        val stopIntent=Intent(context,StopWatchService::class.java).apply {
            putExtra(STOPWATCH_STATE,StopWatchState.STOPPED.name)
        }
        return PendingIntent.getService(context, STOP_REQUEST_CODE,stopIntent, flag)
    }

    fun resumePendingIntent(context: Context):PendingIntent{
        val resumeIntent=Intent(context,StopWatchService::class.java).apply {
            putExtra(STOPWATCH_STATE,StopWatchState.STARTED.name)
        }
        return PendingIntent.getService(context, RESUME_REQUEST_CODE,resumeIntent, flag)
    }

    fun cancelPendingIntent(context: Context):PendingIntent{
        val cancelIntent=Intent(context,StopWatchService::class.java).apply {
            putExtra(STOPWATCH_STATE,StopWatchState.CANCELED.name)
        }
        return PendingIntent.getService(context, CANCEL_REQUEST_CODE,cancelIntent, flag)
    }

    fun triggerService(context: Context,action:String){
        Intent(context,StopWatchService::class.java).apply {
            this.action=action
            context.startService(this)
        }
    }
}