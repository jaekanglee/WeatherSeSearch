package com.example.idusproject.model.remote.entity

import com.example.idusproject.model.remote.Result
object SearchWordEntityMapper : NetworkMapper<List<SearchWordEntity>>() {
    override fun mapTo(data: List<SearchWordEntity>): Result<List<SearchWordEntity>> {
        return Result.Success(data)

    }
}