package com.example.hibidroid.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.hibidroid.Model.Hibicode.HibicodeEntity
import com.example.hibidroid.Model.Hibicode.HibicodeRepository

class MainViewModel(private val repository: HibicodeRepository) : ViewModel() {

    var allhibicodes: LiveData<List<HibicodeEntity>> = repository.allHibicodes.asLiveData()

    var liveDataText:String =""



}