package com.example.testapplication.main.di

import com.example.testapplication.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    //Main
    viewModel { MainViewModel() }

}