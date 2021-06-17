package com.example.testapplication.main

import android.app.Application
import android.content.Context
import com.example.testapplication.main.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApplication : Application()  {

    companion object {
        private lateinit var instance : Application
        fun getContext() : Context = instance
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestApplication)
            modules(
                viewModelModule
            )
        }
    }
}