package com.example.idusproject.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.idusproject.databinding.ItemTitleLineBinding
import com.example.idusproject.viewmodel.MainActViewModel

class WeatherTitleHolder(
    val binding: ItemTitleLineBinding,
    val viewModel: MainActViewModel
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(pos: Int) {
        binding.viewmodel = viewModel
        binding.pos = pos
        binding.executePendingBindings()
    }
}