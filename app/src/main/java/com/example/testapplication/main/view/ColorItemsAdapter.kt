package com.example.testapplication.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.databinding.ItemColorBinding
import com.example.testapplication.main.model.ColorItem

class ColorItemsAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var items: MutableList<ColorItem>? = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_color,
            parent,
            false
        ) as ItemColorBinding
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items?.isNotEmpty() == true) {
            holder.bind(items!![position])
        }

    }

    override fun getItemCount(): Int = items?.size ?: 0

    fun update(colors: MutableList<ColorItem>?) {
        colors?.let {
            items = it
        }
    }
}

class ViewHolder(private val binding: ItemColorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(colorItem: ColorItem) {
        binding.color = colorItem
    }
}