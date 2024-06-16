package com.example.hibidroid.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hibidroid.Model.Hibicode.HibicodeEntity
import com.example.hibidroid.Model.Hibicode.HibicodeRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: HibicodeRepository) : ViewModel() {

    var allhibicodes: LiveData<List<HibicodeEntity>> = repository.allHibicodes.asLiveData()

    fun insert(hibicode: HibicodeEntity)= viewModelScope.launch{
        repository.insert(hibicode)
    }

    fun delete(hibicode: HibicodeEntity)= viewModelScope.launch{
        repository.delete(hibicode)
    }

    fun update(hibicode: HibicodeEntity)= viewModelScope.launch{
        repository.update(hibicode)
    }

    var liveDataText:String =""
}

class MainViewModelFactory(private val repository: HibicodeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}