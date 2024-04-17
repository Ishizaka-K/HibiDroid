package com.example.hibidroid.Connector

import android.content.Context
import android.net.ConnectivityManager
import android.telecom.Call
import android.util.Log
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


class KintaiConnector {
    //接続先のurl
    private var urlStr = ""

    //レスポンス用文字列
    private var respStr = ""

    //レスポンス用JSONArray
    private var respJsonArr: JSONArray? = null

    //待機処理用
    private var Roop_Count = 0
    private var Timeout_Sec = 0
    private var countDownLatch: CountDownLatch? = null

    //setterとgetter
    fun getUrl(): String {
        return urlStr
    }

    fun setUrl(urlStr: String) {
        this.urlStr = urlStr
    }

    //setterとgetter(レスポンス用　非同期処理対応)
    @Throws(InterruptedException::class)
    fun getrespStr(): String {
        countDownLatch!!.await(Timeout_Sec.toLong(), TimeUnit.SECONDS)
        return respStr
    }

    fun setrespStr(respStr: String) {
        countDownLatch!!.countDown()
        this.respStr = respStr
    }

    @Throws(InterruptedException::class)
    fun getrespJsonArr(): JSONArray? {
        countDownLatch!!.await(Timeout_Sec.toLong(), TimeUnit.SECONDS)
        return respJsonArr
    }

    fun setrespJsonArr(respJsonArr: JSONArray?) {
        countDownLatch!!.countDown()
        this.respJsonArr = respJsonArr
    }

    //コンストラクタ
    fun HttpConnector(Roop_Count: Int, Timeout_Sec: Int) {
        this.Roop_Count = Roop_Count
        this.Timeout_Sec = Timeout_Sec
        countDownLatch = CountDownLatch(Roop_Count)
    }

    //端末がネットワークに接続されているか確認
    fun checkConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.getNetworkCapabilities(cm.activeNetwork)
        return info != null
    }

    //GET実行
    fun doGet(urlStr: String?) {
        val request: Request = Request.Builder()
            .url(urlStr!!)
            .get()
            .build()
        conectHttp(request)
    }

    //POST実行
    fun doPost(url: String?, postdata: String?) {
        val body: RequestBody =
            RequestBody.create(postdata, "application/json; charset=Shift_JIS".toMediaType())
        val request: Request = Request.Builder()
            .url(url!!)
            .post(body)
            .build()
        conectHttp(request)
    }

    //Requestを送りResponseを受け取る
    private fun conectHttp(request: Request) {
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback() {
            //通信に失敗した場合
            fun onFailure(call: Call?, e: IOException) {
                e.printStackTrace()
                setrespStr("")
                setrespJsonArr(null)
                Log.d("状態", "Failure")
            }

            //通信に成功した場合
            fun onResponse(call: Call?, response: Response) {
                try {
                    setrespStr(response.body().string())
                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.d("状態", "Empty")
                    setrespStr("")
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                    Log.d("状態", "Empty")
                    setrespStr("")
                }
                try {
                    setrespJsonArr(JSONArray(respStr))
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Log.d("状態", "Empty")
                    setrespJsonArr(null)
                }
            }
        })
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT_MILLISECONDS.toLong(), TimeUnit.MILLISECONDS)
        .readTimeout(READ_TIMEOUT_MILLISECONDS.toLong(), TimeUnit.MILLISECONDS)
        .build()

    override fun startGetRequest() {
        // Requestを作成
        val request = Request.Builder()
            .url("ここにURL")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                // Responseの読み出し
                val responseBody = response.body?.string().orEmpty()
                // 必要に応じてCallback
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
                // 必要に応じてCallback
            }
        })
    }



    //POST
    private val client = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT_MILLISECONDS.toLong(), TimeUnit.MILLISECONDS)
        .readTimeout(READ_TIMEOUT_MILLISECONDS.toLong(), TimeUnit.MILLISECONDS)
        .build()

    private val JSON_MEDIA = "application/json; charset=utf-8".toMediaType()

    override fun startPostRequest() {
        // Bodyのデータ（サンプル）
        val sendDataJson = "{\"id\":\"1234567890\",\"name\":\"hogehoge\"}"

        // Requestを作成
        val request = Request.Builder()
            .url(urlStr)
            .post(sendDataJson.toRequestBody(JSON_MEDIA))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                // Responseの読み出し
                val responseBody = response.body?.string().orEmpty()
                // 必要に応じてCallback
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
                // 必要に応じてCallback
            }
        })
    }
}