package com.example.idusproject.model.usecase

import com.example.idusproject.model.remote.Repository
import com.example.idusproject.model.remote.Result
import com.example.idusproject.model.remote.RepositoryImpl
import com.example.idusproject.model.remote.entity.SearchWordEntity
import io.reactivex.Single

class SearchUseCase(val searchApi: Repository) :
    SingleUseCase<String, List<SearchWordEntity>>() {


    override fun execute(parameter: String): Single<Result<List<SearchWordEntity>>> {
        return searchApi.getSearchWord(parameter)
    }
}