package com.example.idusproject.model.remote

import com.example.idusproject.model.remote.entity.SearchWoeidEntity
import com.example.idusproject.model.remote.entity.SearchWordEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WordSearchApi {

    @GET("api/location/search/")
    fun searchWordLocationApi(
        @Query("query") param :String)
            : Single<Response<List<SearchWordEntity>>>

    @GET("api/location/{woeid}/")
    fun searchwoeidApi(
        @Path("woeid") id : Long
    ):Single<Response<SearchWoeidEntity>>
}