package com.example.idusproject.model.remote

import com.example.idusproject.model.remote.entity.Location
import com.example.idusproject.model.remote.entity.WoeidEntity
import io.reactivex.Single
import retrofit2.Response

class LocationRemoteDataSource(private val api: WordSearchApi) {
     fun searchWord(params:String): Single<Response<List<Location>>> {
        return api.searchWordLocationApi(params)
    }

     fun searchWoeid(
         id:Long
    ): Single<Response<WoeidEntity>> {
        return api.searchwoeidApi(id)
    }

}