package com.example.testapplication.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var colorsAdapter: ColorItemsAdapter
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        initViews()
        setupObservers()
    }

    private fun initViews() {
        colorsAdapter = ColorItemsAdapter()
        binding.mainRecyclerView.adapter = colorsAdapter
    }

    private fun setupObservers() {
        viewModel.colorsResult.observe(this, { result ->
            result.colors?.let { colors ->
                colorsAdapter.update(colors)
            }
        })
    }
}