package com.example.idusproject.model.remote

import com.example.idusproject.model.remote.entity.Location
import com.example.idusproject.model.remote.entity.WoeidEntity
import io.reactivex.Single

class RepositoryImpl(val dataSource : LocationRemoteDataSource) :Repository{
    override fun getSearchWord(param: String): Single<List<Location>> {
        return dataSource.searchWord(param)
            .flatMap {
                if(it.isSuccessful){
                    Single.just(it.body())
                }
                else{
                    throw  Exception(it.message())
                }
            }
    }

    override fun getSearchWoeid(id: Long): Single<WoeidEntity> {
        return dataSource.searchWoeid(id)
                .map {
                    if(it.isSuccessful){
                       it.body()
                    }
                    else{
                        throw  Exception(it.message())
                    }
                }
    }

}