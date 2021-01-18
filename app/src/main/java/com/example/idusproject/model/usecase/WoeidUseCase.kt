package com.example.idusproject.model.usecase

import com.example.idusproject.model.remote.Repository
import com.example.idusproject.model.remote.entity.WoeidEntity
import io.reactivex.Single

class WoeidUseCase(val repository: Repository) :SingleUseCase<Long, WoeidEntity>(){
    override fun execute(parameter: Long): Single<WoeidEntity> {
        return repository.getSearchWoeid(parameter)
    }

}