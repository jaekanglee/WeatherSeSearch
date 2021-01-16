package com.example.idusproject.model.remote

import com.example.idusproject.model.remote.entity.SearchWoeidEntity
import com.example.idusproject.model.remote.entity.SearchWordEntity
import io.reactivex.Single
import retrofit2.Response

class LocationRemoteDataSource(private val api: WordSearchApi) {
     fun searchWord(params:String): Single<Response<List<SearchWordEntity>>> {
        return api.searchWordLocationApi(params)
    }

     fun searchWoeid(
         id:Long
    ): Single<Response<SearchWoeidEntity>> {
        return api.searchwoeidApi(id)
    }

}