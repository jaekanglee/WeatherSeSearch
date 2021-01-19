package com.example.idusproject.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.idusproject.model.remote.entity.LocationDetailEntity
import com.example.idusproject.model.usecase.LocationUseCase
import com.example.idusproject.model.usecase.LocationDetailUseCase
import com.example.idusproject.utils.SingleLiveEvent
import com.example.idusproject.view.entity.LineType
import com.example.idusproject.view.entity.RecyclerViewItemEntity
import com.example.idusproject.view.entity.ViewItemMapper
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel(
    val locationUseCase: LocationUseCase,
    val searchLocationDetailUseCase: LocationDetailUseCase
) : BaseLifeCyclerViewModel() {

    val lAdapterNotify = SingleLiveEvent<Boolean>()

    private val _mainProgressBarState = MutableLiveData<Int>().apply {
        this.value = View.VISIBLE
    }
    val mainProgressBarState: LiveData<Int>
        get() = _mainProgressBarState

    private val _refreshState = MutableLiveData<Boolean>()
    val refreshState: LiveData<Boolean>
        get() = _refreshState

    private val viewWeatherList: ArrayList<RecyclerViewItemEntity> by lazy {
        ArrayList<RecyclerViewItemEntity>()
    }

    fun setMainProgressStatus(state: Boolean) {
        if (state) {
            _mainProgressBarState.value = View.VISIBLE
        } else {
            _mainProgressBarState.value = View.GONE
        }
    }

    fun getLocationApi() {
        viewWeatherList.clear()
        setAdapterNotifyState(true)

        this(locationUseCase("se")
            .flatMap { result ->
                val de = result.map { inner ->
                    getLocationDetail(inner.woeid)
                }
                Single.zip(de) { it.iterator() as Iterator<LocationDetailEntity> }
            }
            .map {
                ViewItemMapper.mapperIteratorToList(it)
            }
            .subscribeBy(
                onSuccess = {
                    viewWeatherList.addAll(it)
                    setAdapterNotifyState(true)
                    setMainProgressStatus(false)
                    setRefreshState(false)
                },
                onError = {
                    setErrorMsg(it.localizedMessage)
                }
            ))
    }

    fun getLocationDetail(woeId: Long): Single<LocationDetailEntity> {
        return searchLocationDetailUseCase(woeId)
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

    /*
    * API규격을 보았을때 consolidated_weather 중 0-> 오늘 1->내일 임
    * 실제로  index에 대한 하드코딩은 지양해야 맞지만, 규격이 변치 않을 상황이므로 하드코딩
    * 만약 유동적인 api라면 이런 방법은 반드시 배제해야 함
    * */
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
        _refreshState.value = state
    }
}