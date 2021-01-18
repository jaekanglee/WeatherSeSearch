package com.example.idusproject.model.usecase

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io

abstract class SingleUseCase<T, R>() {


    @SuppressLint("CheckResult")
    operator fun invoke(parameter: T): Single<R> {
        return execute(parameter).subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    abstract fun execute(parameter: T): Single<R>
}
