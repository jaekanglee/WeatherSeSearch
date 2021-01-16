package com.example.idusproject.model.remote.entity

import com.example.idusproject.model.remote.Result

abstract class NetworkMapper<R> {
    fun map(data: retrofit2.Response<R>): Result<R> {
        return if (data.isSuccessful) {
            data.body()?.let {
                mapTo(it)
            } ?: run {
                Result.Error("server")
            }
        } else {
            Result.Error("network")
        }
    }

    abstract fun mapTo(data: R): Result<R>
}