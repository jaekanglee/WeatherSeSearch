package com.example.idusproject.model.remote

import com.example.idusproject.model.remote.entity.Location
import com.example.idusproject.model.remote.entity.LocationDetailEntity
import io.reactivex.Single

interface Repository {
    fun getSearchWord(param: String) : Single<List<Location>>
    fun getSearchWoeid(id:Long) : Single<LocationDetailEntity>
}