package com.example.idusproject.di

import com.example.idusproject.model.remote.LocationRemoteDataSource
import com.example.idusproject.model.remote.Repository
import com.example.idusproject.model.remote.RepositoryImpl
import com.example.idusproject.model.remote.LocationSearchApi
import com.example.idusproject.model.usecase.LocationUseCase
import com.example.idusproject.model.usecase.LocationDetailUseCase
import com.example.idusproject.utils.BASE_URL
import com.example.idusproject.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val client = OkHttpClient.Builder().addInterceptor(interceptor).build();

val retrofit: Retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
private val SEARCH_API: LocationSearchApi = retrofit.create(LocationSearchApi::class.java)

val repositoryModule = module {
    factory<Repository> { RepositoryImpl(get()) }
}
val dataSourceModule = module {
    factory { LocationRemoteDataSource(get()) }
}


private val networkModule = module {
    single { SEARCH_API }
}
private val useCaseModule = module {
    factory { LocationUseCase(get()) }
    factory { LocationDetailUseCase(get()) }

}

val viewModelModule = module {
    viewModel { MainViewModel(get(),get()) }
}

 val moduleList = listOf(
    networkModule,useCaseModule,viewModelModule,repositoryModule,dataSourceModule
)



