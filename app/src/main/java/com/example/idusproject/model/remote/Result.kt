package com.example.idusproject.model.remote


sealed class Result<out R>() {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMsg: String) : Result<Nothing>()
}