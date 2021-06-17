package com.example.testapplication.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.main.model.ErrorResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.text.ParseException

abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    //val success = MutableLiveData<Event<Unit>>()

    protected fun launchDataLoad(
        customLoading: MutableLiveData<Boolean>? = null,
        customError: MutableLiveData<String>? = null,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                if (customLoading != null) {
                    customLoading.postValue(true)
                } else {
                    _loading.postValue(true)
                }
//                EspressoIdlingResource.increment()
                block()
            } catch (error: Exception) {
                if (error is HttpException) {
                    val errorBody = error.response()?.errorBody()?.string()
                    if (errorBody?.isNotEmpty() == true) {
                        try {
                            val errorObject = JSONObject(errorBody)
                            errorObject.let {
                                val errorResponse =
                                    ErrorResponse(
                                        it.getString("code"),
                                        it.getString("description"),
                                        it.getString("userMessage")
                                    )
                                errorResponse.userMessage?.let { userMessage ->
                                    showError(userMessage, customError)
                                }
                            }
                        } catch (exception: ParseException) {
                            showError(customError = customError)
                        } catch (exception: JSONException) {
                            showError(customError = customError)
                        }
                    } else {
                        showError(customError = customError)
                    }
                } else {
                    showError(customError = customError)
                }
            } finally {
                if (customLoading != null) {
                    customLoading.postValue(false)
                } else {
                    _loading.postValue(false)
                }
//                EspressoIdlingResource.decrement()
            }
        }
    }

    private fun showError(message: String? = null, customError: MutableLiveData<String>? = null) {
//        if (customError != null) {
//            customError.postValue(message ?: getApplication<GameficApplication>()
//                .applicationContext.getString(R.string.network_error_generic))
//        } else {
//            _error.postValue(message ?: getApplication<GameficApplication>()
//                .applicationContext.getString(R.string.network_error_generic))
//        }
    }

}