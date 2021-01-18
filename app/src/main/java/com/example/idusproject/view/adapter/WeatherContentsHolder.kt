package com.example.idusproject.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.idusproject.databinding.ItemContentsLineBinding
import com.example.idusproject.viewmodel.MainViewModel

class WeatherContentsHolder(
    val binding : ItemContentsLineBinding,
    val viewModel: MainViewModel,
    val requestManager: RequestManager
) :RecyclerView.ViewHolder(binding.root){

    fun clearImageRequest(){
        requestManager.clear(binding.imageView)
        requestManager.clear(binding.imageView2)

    }


    fun bind(pos : Int){
        binding.viewmodel= viewModel
        binding.pos=pos
        binding.requestManager=requestManager
        binding.executePendingBindings()
    }
}