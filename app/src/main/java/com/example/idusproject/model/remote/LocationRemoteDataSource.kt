package com.example.idusproject.model.remote

import com.example.idusproject.model.remote.entity.Location
import com.example.idusproject.model.remote.entity.LocationDetailEntity
import io.reactivex.Single
import retrofit2.Response

class LocationRemoteDataSource(private val api: LocationSearchApi) {
     fun searchWord(params:String): Single<Response<List<Location>>> {
        return api.searchWordLocationApi(params)
    }

     fun searchWoeid(
         id:Long
    ): Single<Response<LocationDetailEntity>> {
        return api.searchwoeidApi(id)
    }

}