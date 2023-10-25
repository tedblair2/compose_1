package com.example.compose1.internet

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn

class NetworkViewModel(networkStatusService: NetworkStatusService):ViewModel() {

    val currentNetworkStatus=networkStatusService
        .getNetworkStatus()
        .catch { Log.d("NetworkError", it.message.toString()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),NetworkStatus.Unknown)
}