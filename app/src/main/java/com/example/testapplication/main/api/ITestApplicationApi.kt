package com.example.testapplication.main.api

import com.example.testapplication.main.model.Colors
import retrofit2.http.GET

interface ITestApplicationApi {

    @GET("colors")
    suspend fun getColors(): Colors

}