package com.example.myapprapptest.di

import android.app.Application
import android.content.Context
import com.example.myapprapptest.repository.RepositoryMovie
import com.example.myapprapptest.repository.database.DaoMovies
import com.example.myapprapptest.usecases.NetworkTopListMoviesImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(private val mApplication: Application) {

    @Provides
    fun getApplicationContext() : Context{
        return mApplication.applicationContext
    }

    @Provides
    fun getRepository(dao: DaoMovies, serviceTopMovies: NetworkTopListMoviesImpl) : RepositoryMovie {
        return RepositoryMovie(dao, serviceTopMovies)
    }
}