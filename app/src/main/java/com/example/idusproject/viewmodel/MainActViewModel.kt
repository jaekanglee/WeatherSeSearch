package com.example.idusproject.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.idusproject.model.remote.entity.Location
import com.example.idusproject.model.remote.entity.WoeidEntity
import com.example.idusproject.model.usecase.SearchUseCase
import com.example.idusproject.model.usecase.WoeidUseCase
import com.example.idusproject.utils.SingleLiveEvent
import com.example.idusproject.view.entity.LineType
import com.example.idusproject.view.entity.RecyclerViewItemEntity
import com.example.idusproject.view.entity.ViewItemMapper
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class MainActViewModel(
    val searchUseCase: SearchUseCase,
    val searchWoeidUseCase: WoeidUseCase
) : BaseLifeCyclerViewModel() {

    val lAdapterNotify = SingleLiveEvent<Boolean>()

    private val showMainProgressBar = MutableLiveData<Int>()
    val _showMainProgressBar: LiveData<Int>
        get() = showMainProgressBar

    private val refreshState = MutableLiveData<Boolean>()
    val _refreshState: LiveData<Boolean>
        get() = refreshState

    private val viewWeatherList: ArrayList<RecyclerViewItemEntity> by lazy {
        ArrayList<RecyclerViewItemEntity>()
    }

    fun setMainProgressStatus(state: Int) {
        showMainProgressBar.value = state
    }

    fun getSearchWordApiResult() {
        viewWeatherList.clear()
        setAdapterNotifyState(true)

        this(searchUseCase("se")
            .flatMap { result ->
                val de = result.map { inner ->
                    searchWoeidUseCase(inner.woeid)
                }
                Single.zip(de) { it.iterator() as Iterator<WoeidEntity> }
            }
            .map {
                val list = ArrayList<RecyclerViewItemEntity>().apply {
                    this.add(ViewItemMapper.mapperViewItemEntity(null, LineType.TITLE))
                }
                it.forEach {
                    list.add(ViewItemMapper.mapperViewItemEntity(it, LineType.CONTENTS))
                }
                list
            }
            .subscribeBy(
                onSuccess = {
                    viewWeatherList.addAll(it)
                    setAdapterNotifyState(true)
                    setMainProgressStatus(View.GONE)
                    setRefreshState(false)
                },
                onError = {
                    setErrorMsg(it.localizedMessage)
                }
            ))

    }

    fun setAdapterNotifyState(state: Boolean) {
        lAdapterNotify.value = state
    }

    fun getCityListSize(): Int {
        return viewWeatherList.size
    }

    fun getCityName(pos: Int): String? {
        return viewWeatherList[pos].title
    }

    fun getTodayWeatherTemp(pos: Int): String? {
        //흠 성능 다이어트를 하면 이런 코드는 고쳐야만 할 부분
        return "${viewWeatherList[pos].consolidated_weather?.get(0)?.the_temp}".split(".")[0] + "℃"
    }

    fun getTodayWeatherState(pos: Int): String? {
        return viewWeatherList[pos].consolidated_weather?.get(0)?.weather_state_name
    }

    fun getTodayHumidity(pos: Int): String? {
        return "${viewWeatherList[pos].consolidated_weather?.get(0)?.humidity}%"
    }

    fun getTodayAbbr(pos: Int): String? {
        return viewWeatherList[pos].consolidated_weather?.get(0)?.weather_state_abbr

    }

    fun getTommorowWeatherTemp(pos: Int): String? {
        return "${viewWeatherList[pos].consolidated_weather?.get(1)?.the_temp}".split(".")[0] + "℃"
    }

    fun getTommorowWeatherState(pos: Int): String? {
        return viewWeatherList[pos].consolidated_weather?.get(1)?.weather_state_name
    }

    fun getTommorowHumidity(pos: Int): String? {
        return "${viewWeatherList[pos].consolidated_weather?.get(1)?.humidity}%"
    }

    fun getTommorowAbbr(pos: Int): String? {
        Log.d(
            "TommorowAbbr",
            viewWeatherList[pos].consolidated_weather?.get(1)?.weather_state_abbr ?: "Null"
        )
        return viewWeatherList[pos].consolidated_weather?.get(1)?.weather_state_abbr

    }

    fun getItemEntityViewType(pos: Int): Int {
        return when (viewWeatherList[pos].viewType) {
            LineType.TITLE -> {
                0
            }
            LineType.CONTENTS -> {
                1
            }

        }
    }

    fun setRefreshState(state: Boolean) {
        refreshState.value = state
    }
}