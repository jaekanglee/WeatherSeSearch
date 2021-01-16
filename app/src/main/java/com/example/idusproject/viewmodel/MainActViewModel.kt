package com.example.idusproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idusproject.model.remote.entity.SearchWordEntity
import com.example.idusproject.model.usecase.SearchUseCase
import com.example.idusproject.utils.Event
import com.example.idusproject.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

class MainActViewModel(
    val searchUseCase: SearchUseCase
) : BaseLifeCyclerViewModel() {
    private val lAdapterNotify = SingleLiveEvent<Boolean>()

    val showMainProgressBar = MutableLiveData<Int>()
    val _showMainProgressBar: LiveData<Int>
        get() = showMainProgressBar
    private val _searchWordList = MediatorLiveData<Any>()
    val searchWordList: LiveData<Any>
        get() = _searchWordList

    private val allWordEntityList = ArrayList<SearchWordEntity>()
    private val searchWordResult = searchUseCase.observe()

    init {

        searchWordResult.onSuccess(_searchWordList) { result ->
            allWordEntityList.clear()
            allWordEntityList.addAll(result.data)
        }
        searchWordResult.onError(_error) { result ->
            callNetworkError()
        }
    }


    fun setMainProgressStatus(state: Int) {
        showMainProgressBar.value = state
    }


    fun setRefresh() {

    }


    fun getSearchWordApiResult() {
        Log.e("ViewModel getSearchWordApiResult()","??")
        Log.e("ViewModel init","??")

        this(searchUseCase("se"))

    }

    private fun callNetworkError() {
        _error.value = Event("network")
    }


}