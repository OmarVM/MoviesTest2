package com.example.myapprapptest.di

import com.example.myapprapptest.repository.network.ConstantServer
import com.example.myapprapptest.repository.network.IAPIMovie
import com.example.myapprapptest.usecases.NetworkTopListMoviesImpl
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstantServer.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getServiceAPI(retrofit: Retrofit) : IAPIMovie {
        return retrofit.create(IAPIMovie::class.java)
    }

    @Provides
    @Singleton
    fun getTopListMoviesFromNetwork(api: IAPIMovie): NetworkTopListMoviesImpl {
        return NetworkTopListMoviesImpl(api)
    }
}