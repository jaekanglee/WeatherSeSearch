package com.example.idusproject.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.idusproject.databinding.ItemContentsLineBinding
import com.example.idusproject.databinding.ItemTitleLineBinding
import com.example.idusproject.viewmodel.MainActViewModel

class WeatherAdapter(val requestManager: RequestManager, val viewmodel: MainActViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("ViewType", "${viewType}")
        when (viewType) {
            0 -> {
                val binding =
                    ItemTitleLineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return WeatherTitleHolder(
                    binding,
                    viewmodel
                )
            }
            else -> {
                val binding = ItemContentsLineBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return WeatherContentsHolder(
                    binding,
                    viewmodel,
                    requestManager
                )
            }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if(holder is WeatherContentsHolder){
            holder.clearImageRequest()
        }
    }

    override fun getItemCount(): Int {
        return viewmodel.getCityListSize()
    }

    override fun getItemViewType(position: Int): Int {
        return viewmodel.getItemEntityViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WeatherTitleHolder) {
            holder.bind(position)
        } else if (holder is WeatherContentsHolder) {
            holder.bind(position)
        } else {

        }
    }


}