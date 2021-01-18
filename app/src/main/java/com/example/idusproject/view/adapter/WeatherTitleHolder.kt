package com.example.idusproject.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.idusproject.databinding.ItemTitleLineBinding
import com.example.idusproject.viewmodel.MainViewModel

class WeatherTitleHolder(
    val binding: ItemTitleLineBinding,
    val viewModel: MainViewModel
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(pos: Int) {
        binding.viewmodel = viewModel
        binding.pos = pos
        binding.executePendingBindings()
    }
}