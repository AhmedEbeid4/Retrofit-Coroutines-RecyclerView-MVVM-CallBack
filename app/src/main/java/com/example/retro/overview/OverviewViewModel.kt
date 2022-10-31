package com.example.retro.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retro.network.MarsApi
import com.example.retro.network.MarsProperty

import kotlinx.coroutines.launch


class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    init {
        getMarsRealEstateProperties()
    }


    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            try {
                var listResult = MarsApi.retrofitService.getProperties()
                _properties.value=listResult.await()

            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }

    }
}
