package com.example.myapprapptest.di

import android.content.Context
import com.example.myapprapptest.repository.database.DBMovieHelper
import com.example.myapprapptest.repository.database.DaoMovies
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun getDao(context: Context) : DaoMovies {
        return DBMovieHelper(context).daoItem()
    }
}