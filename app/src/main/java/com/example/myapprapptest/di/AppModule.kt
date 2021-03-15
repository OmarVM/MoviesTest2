package com.example.myapprapptest.di

import android.app.Application
import android.content.Context
import com.example.myapprapptest.repository.RepositoryMovie
import com.example.myapprapptest.repository.database.DaoMovies
import com.example.myapprapptest.usecases.NetworkPopularListMoviesImpl
import com.example.myapprapptest.usecases.NetworkTopListMoviesImpl
import com.example.myapprapptest.view.AdapterMovies
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val mApplication: Application) {

    @Provides
    fun getApplicationContext() : Context{
        return mApplication.applicationContext
    }

    @Provides
    @Singleton
    fun getRepository(dao: DaoMovies, serviceTopMovies: NetworkTopListMoviesImpl, servicePopularMovies: NetworkPopularListMoviesImpl) : RepositoryMovie {
        return RepositoryMovie(dao, serviceTopMovies, servicePopularMovies)
    }

    //Views

    @Provides
    fun getApdaterMovies(): AdapterMovies {
        return AdapterMovies(arrayListOf())
    }
}