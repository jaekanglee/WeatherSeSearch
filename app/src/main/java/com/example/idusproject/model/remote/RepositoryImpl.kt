package com.example.idusproject.model.remote

import com.example.idusproject.model.remote.entity.SearchWoeidEntity
import com.example.idusproject.model.remote.entity.SearchWordEntity
import com.example.idusproject.model.remote.entity.SearchWordEntityMapper
import io.reactivex.Single

class RepositoryImpl(val dataSource : LocationRemoteDataSource) :Repository{
    override fun getSearchWord(param: String): Single<Result<List<SearchWordEntity>>> {
        return dataSource.searchWord(param).map (SearchWordEntityMapper::map )
    }

    override fun getSearchWoeid(id: Long): Single<Result<SearchWoeidEntity>> {
        return dataSource.searchWoeid(111).map { null }
    }

}