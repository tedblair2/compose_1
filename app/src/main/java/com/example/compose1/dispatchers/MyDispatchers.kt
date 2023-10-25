package com.example.compose1.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface MyDispatchers {
    val main:CoroutineDispatcher
    val io:CoroutineDispatcher
    val default:CoroutineDispatcher
}