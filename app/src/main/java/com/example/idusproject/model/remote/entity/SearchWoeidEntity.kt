package com.example.idusproject.model.remote.entity

import com.google.gson.annotations.SerializedName

data class SearchWoeidEntity(
    @SerializedName("weather_state_name")
    val state_name:String,
    @SerializedName("weather_state_abbr")
    val state_abbr:String,
    @SerializedName("the_temp")
    val current_temp :String,
    @SerializedName("humidity")
    val humidity : String
)