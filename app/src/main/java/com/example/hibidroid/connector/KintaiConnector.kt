package com.example.hibidroid.connector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hibidroid.viewModels.MainViewModel
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit


class KintaiConnector {
    fun startGetRequest(client: OkHttpClient,request: Request): String {

        var responseBody: String = String()
        var resposeJson: Response

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: okhttp3.Call, response: Response) {
                resposeJson = response
                responseBody = response.headers.getDate("date").toString()
                Log.d("Response", responseBody)
                MainViewModel().liveDataText=responseBody.toString()
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("Error", e.toString())
                // 必要に応じてCallbacksuru
            }
        })
        return responseBody
    }


    fun startPostRequest() {

        val JSON_MEDIA = "application/json; charset=utf-8".toMediaType()

        var client: OkHttpClient = OkHttpClient().newBuilder()
            .readTimeout((15 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout((20 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .connectTimeout((20 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .build()

        // Bodyのデータ（サンプル）
        val sendDataJson = "{\"id\":\"1234567890\",\"name\":\"hogehoge\"}"

        // Requestを作成
        val request = Request.Builder()
            .url("inflater")
            .post(sendDataJson.toRequestBody(JSON_MEDIA))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: okhttp3.Call, response: Response) {
                // Responseの読み出し
                val responseBody = response.headers.toString().orEmpty()
                // 必要に応じてCallback
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("Error", e.toString())
                // 必要に応じてCallback
            }
        })
    }
}