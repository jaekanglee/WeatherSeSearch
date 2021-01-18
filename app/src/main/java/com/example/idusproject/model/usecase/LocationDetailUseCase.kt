package com.example.idusproject.model.usecase

import com.example.idusproject.model.remote.Repository
import com.example.idusproject.model.remote.entity.LocationDetailEntity
import io.reactivex.Single

class LocationDetailUseCase(val repository: Repository) :SingleUseCase<Long, LocationDetailEntity>(){
    override fun execute(parameter: Long): Single<LocationDetailEntity> {
        return repository.getSearchWoeid(parameter)
    }

}