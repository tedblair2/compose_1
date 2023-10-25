package com.example.compose1.internet

sealed class NetworkStatus{
    object Unknown:NetworkStatus()
    object Connected:NetworkStatus()
    object Disconnected:NetworkStatus()
}
