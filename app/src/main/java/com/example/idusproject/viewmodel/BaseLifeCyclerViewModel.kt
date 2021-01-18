package com.example.idusproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idusproject.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseLifeCyclerViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val error = SingleLiveEvent<String>()
    val _error: LiveData<String>
        get() = error

    operator fun invoke(disposable: Disposable) {
        this.disposable.add(disposable)
    }



    fun setErrorMsg(msg:String?){
        msg?.let {
            error.value=it
        }
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}