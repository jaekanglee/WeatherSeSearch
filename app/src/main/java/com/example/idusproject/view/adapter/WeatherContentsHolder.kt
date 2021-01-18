package com.example.idusproject.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.idusproject.databinding.ItemContentsLineBinding
import com.example.idusproject.databinding.ItemTitleLineBinding
import com.example.idusproject.viewmodel.MainActViewModel

class WeatherContentsHolder(
    val binding : ItemContentsLineBinding,
    val viewModel: MainActViewModel,
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