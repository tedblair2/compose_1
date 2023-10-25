package com.example.compose1.internet

import kotlinx.coroutines.flow.Flow

interface NetworkStatusService {
    fun getNetworkStatus():Flow<NetworkStatus>
}