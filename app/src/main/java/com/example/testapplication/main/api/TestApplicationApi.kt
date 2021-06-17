package com.example.testapplication.main.api

import com.example.testapplication.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TestApplicationApi {

    companion object {

        private const val BASE_URL = "https://literate-splashy-process.glitch.me/"

        private fun getClient(baseUrl: String? = null): Retrofit {
            val clientBuilder = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addInterceptor(AuthenticatorInterceptor())
//                .addInterceptor(UnauthorizedInterceptor(GameficApplication.getContext()))
//                .addInterceptor(AppVersionInterceptor(GameficApplication.getContext()))
//                .addInterceptor(ConnectivityInterceptor(GameficApplication.getContext()))
//                .addInterceptor(AcceptLanguageHeaderInterceptor())
//                .addInterceptor(TimeoutInterceptor())
//                .authenticator(TokenAuthenticator(GameficApplication.getContext()))
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                clientBuilder.addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                )
            }

            val client = clientBuilder.build()

            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .setPrettyPrinting()
                .create()

            return Retrofit.Builder()
                .baseUrl(baseUrl ?: BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        fun getApi(baseUrl: String? = null): ITestApplicationApi =
            getClient(baseUrl).create(ITestApplicationApi::class.java)
    }
}