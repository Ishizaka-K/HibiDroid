package com.example.hibidroid.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.hibidroid.connector.ConnectorUtil

import com.example.hibidroid.R
import com.example.hibidroid.connector.KintaiConnector
import com.example.hibidroid.databinding.FragmentMainBinding
import com.example.hibidroid.viewModels.MainViewModel

class MainFragment : Fragment() {
    lateinit var mRootView:View
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(inflater,container,false)

        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        mRootView = binding.root

        mRootView.findViewById<Button>(R.id.button).setOnClickListener {
            val url = "https://api.open-meteo.com/v1/forecast?latitude=35.6785&longitude=139.6823&current_weather=true"
            binding.message.text= KintaiConnector().startGetRequest(ConnectorUtil().createClient(),ConnectorUtil().createRequest(url))

            showUrlText()
            Log.d("aaa",viewModel.liveDataText.toString())
        }
        return mRootView
    }

    override fun onResume() {
        super.onResume()
        Log.d("bbb",viewModel.liveDataText.toString())
        showUrlText()
    }
    private fun showUrlText (){
        mRootView.findViewById<TextView>(R.id.message).text = binding.viewModel?.liveDataText.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val url = "https://api.open-meteo.com/v1/forecast?latitude=35.6785&longitude=139.6823&current_weather=true"
            val text = KintaiConnector().startGetRequest(ConnectorUtil().createClient(),ConnectorUtil().createRequest(url))
            //binding.message.text = text
        }
    }

}