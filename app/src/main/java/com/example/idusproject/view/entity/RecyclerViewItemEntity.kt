package com.example.idusproject.view.entity

import com.example.idusproject.model.remote.entity.ConsolidatedWeather

data class RecyclerViewItemEntity(
    val title: String?,
    val viewType: LineType,
    val consolidated_weather: List<ConsolidatedWeather>?
    )