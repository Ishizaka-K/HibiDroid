package com.example.hibidroid.connector

import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class ConnectorUtil {
    fun createClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout((15 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout((20 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .connectTimeout((20 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .build()
    }

    //"https://api.open-meteo.com/v1/forecast?latitude=35.6785&longitude=139.6823&current_weather=true"
    fun createRequest(url: String): Request {
        return Request.Builder()
            .url(url)
            .build()
    }

}