package com.example.idusproject.model.remote

import com.example.idusproject.model.remote.entity.Location
import com.example.idusproject.model.remote.entity.LocationDetailEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationSearchApi {

    @GET("api/location/search/")
    fun searchWordLocationApi(
        @Query("query") param :String)
            : Single<Response<List<Location>>>

    @GET("api/location/{woeid}/")
    fun searchwoeidApi(
        @Path("woeid") id : Long
    ):Single<Response<LocationDetailEntity>>
}