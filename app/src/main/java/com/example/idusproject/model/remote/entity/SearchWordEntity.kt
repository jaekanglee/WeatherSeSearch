package com.example.idusproject.model.remote.entity

import com.google.gson.annotations.SerializedName

data class SearchWordEntity (
    @SerializedName("title")
    val name:String,
    @SerializedName("woeid")
    val woeid:Long
)