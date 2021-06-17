package com.example.testapplication.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapplication.main.BaseViewModel
import com.example.testapplication.main.api.TestApplicationApi
import com.example.testapplication.main.model.Colors

class MainViewModel : BaseViewModel() {

    private var _colorsResult = MutableLiveData<Colors>()
    var colorsResult : LiveData<Colors> = _colorsResult

    init {
        loadColors()
    }

    private fun loadColors() {
        launchDataLoad {
            val result = TestApplicationApi.getApi().getColors()
            _colorsResult.postValue(result)
        }
    }

}