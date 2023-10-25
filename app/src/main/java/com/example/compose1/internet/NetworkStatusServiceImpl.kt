package com.example.compose1.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.example.compose1.dispatchers.MyDispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

class NetworkStatusServiceImpl(private val myDispatchers: MyDispatchers,context:Context) : NetworkStatusService {

    private val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun getNetworkStatus(): Flow<NetworkStatus> = callbackFlow {
        val connectivityCallback=object:NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(NetworkStatus.Connected)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(NetworkStatus.Disconnected)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                trySend(NetworkStatus.Disconnected)
            }
        }

        val request=NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectivityManager.registerNetworkCallback(request,connectivityCallback)

        awaitClose { connectivityManager.unregisterNetworkCallback(connectivityCallback) }
    }
        .distinctUntilChanged()
        .flowOn(myDispatchers.io)
}