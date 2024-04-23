package com.example.hibidroid.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    private val _liveDataText: MutableLiveData<String> =
        MutableLiveData<String>().also { mutableLiveData ->
            mutableLiveData.value = "Ready"
        }
    val liveDataText: LiveData<String>
        get() = _liveDataText

    fun setDataText(text: String) {
        _liveDataText.value = text
    }
}