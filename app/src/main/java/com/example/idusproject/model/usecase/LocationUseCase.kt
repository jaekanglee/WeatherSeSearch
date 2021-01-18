package com.example.idusproject.model.usecase

import com.example.idusproject.model.remote.Repository
import com.example.idusproject.model.remote.entity.Location
import io.reactivex.Single

class LocationUseCase(val searchApi: Repository) :
    SingleUseCase<String, List<Location>>() {


    override fun execute(parameter: String): Single<List<Location>> {
        return searchApi.getSearchWord(parameter)
    }
}