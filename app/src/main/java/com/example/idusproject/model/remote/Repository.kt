package com.example.idusproject.model.remote

import com.example.idusproject.model.remote.entity.SearchWoeidEntity
import com.example.idusproject.model.remote.entity.SearchWordEntity
import io.reactivex.Single

interface Repository {
    fun getSearchWord(param: String) : Single<Result<List<SearchWordEntity>>>
    fun getSearchWoeid(id:Long) : Single<Result<SearchWoeidEntity>>
}