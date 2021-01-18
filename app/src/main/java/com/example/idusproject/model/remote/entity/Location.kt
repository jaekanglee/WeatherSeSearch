package com.example.idusproject.model.remote.entity

import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("title")
    val name:String,
    @SerializedName("woeid")
    val woeid:Long
)