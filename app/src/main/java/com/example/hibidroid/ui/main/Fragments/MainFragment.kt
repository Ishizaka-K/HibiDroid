package com.example.hibidroid.ui.main.Fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.hibidroid.Connector.KintaiConnector

import com.example.hibidroid.R
import com.example.hibidroid.ui.main.ViewModels.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        var mRootView = inflater.inflate(R.layout.fragment_main, container, false)

        mRootView.findViewById<Button>(R.id.button).setOnClickListener {
            var msg = requireView().findViewById<TextView>(R.id.message)

            var client: OkHttpClient = OkHttpClient().newBuilder()
                .readTimeout((15 * 1000).toLong(), TimeUnit.MILLISECONDS)
                .writeTimeout((20 * 1000).toLong(), TimeUnit.MILLISECONDS)
                .connectTimeout((20 * 1000).toLong(), TimeUnit.MILLISECONDS)
                .build()

            val request = Request.Builder()
                .url("https://api.open-meteo.com/v1/forecast?latitude=35.6785&longitude=139.6823&current_weather=true")
                .build()
            var a = KintaiConnector().startGetRequest(client,request)
            msg.text = a
            Log.d("aaa",a.toString())
        }

        return mRootView
    }


}