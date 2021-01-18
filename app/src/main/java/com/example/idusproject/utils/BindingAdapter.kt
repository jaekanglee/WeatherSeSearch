package com.example.idusproject.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.RequestManager
import com.example.idusproject.viewmodel.MainViewModel

object BindingAdapter {


    @JvmStatic
    @BindingAdapter("bind:setterAdapter")
    fun setterAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context).apply {
            this.orientation = RecyclerView.VERTICAL
        }
        recyclerView.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("bind:pullToRefresh")
    fun pullToRefresh(refreshLayout: SwipeRefreshLayout, viewModel: MainViewModel) {
        refreshLayout.setOnRefreshListener {
            viewModel.getLocationApi()
            viewModel.setRefreshState(true)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:refreshIconVisible")
    fun refreshIconVisible(refreshLayout: SwipeRefreshLayout, state: Boolean) {
        refreshLayout.isRefreshing = state
    }

    @JvmStatic
    @BindingAdapter("bind:iconSetter", "bind:requestmanager")
    fun iconSetter(imageView: ImageView, name: String?, requestManager: RequestManager) {
        name?.let {
            val url ="${BASE_IMAGE_URL}${name}.png"
            Log.d("Icon ABR",url)

            requestManager
                .load(url)
                .into(imageView)
        }
    }
//
//    @JvmStatic
//    fun mapperIconString(name: String?): String? {
//        name?.let {
//            return when (it) {
//                "Snow" -> {
//                    "sn"
//                }
//                "Sleet" -> {
//                    "sl"
//                }
//                "Hail" -> {
//                    "h"
//                }
//                "Thunderstorm" -> {
//                    "t"
//                }
//                "Heavy Rain" -> {
//                    "hr"
//                }
//                "Light Rain" -> {
//                    "lr"
//                }
//                "Showers" -> {
//                    "s"
//                }
//                "Heavy Cloud" -> {
//                    "hc"
//                }
//                "Light Cloud" -> {
//                    "lc"
//                }
//                else -> {
//                    "c"
//                }
//            }
//        } ?: kotlin.run {
//            return null
//        }
//
//    }
}