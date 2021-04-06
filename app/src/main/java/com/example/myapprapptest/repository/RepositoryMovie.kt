package com.example.myapprapptest.repository

import android.util.Log
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.repository.database.DaoMovies
import com.example.myapprapptest.usecases.NetworkPopularListMoviesImpl
import com.example.myapprapptest.usecases.NetworkTopListMoviesImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryMovie @Inject constructor(private val mDao: DaoMovies,
                                          private val serviceTopMovies: NetworkTopListMoviesImpl,
                                          private val servicePopularMovies: NetworkPopularListMoviesImpl){

    suspend fun getAllMoviesCache(): List<Movie>{
        if (mDao.getAllContent().isNotEmpty()){
            Log.d("OVM", "All movies fromDB")
            return mDao.getAllContent()
        }
        return arrayListOf()
    }

    @ExperimentalCoroutinesApi
    suspend fun getTopMoviesRepo() = flow {
        if (mDao.getTopMovies().isNotEmpty()){
           Log.d("OVM", "Top fromDB")
           emit(mDao.getTopMovies())
        }else{
            serviceTopMovies.getInfo().collect {
                val insertResult = mDao.insertMovie(it)
                Log.d("OVM", "Value Top Saved : ${insertResult.size}")
                emit(it)
            }
        }
    }

    @ExperimentalCoroutinesApi
    suspend fun getPopularMoviesRepo() =  flow {
        if (mDao.getPopularMovies().isNotEmpty()){
            Log.d("OVM", "Popular fromDB")
            emit(mDao.getPopularMovies())
        }else{
            servicePopularMovies.getInfo().collect {
                val insertPopular = mDao.insertMovie(it)
                Log.d("OVM", "Value Popular Saved : ${insertPopular.size}")
                emit(it)
            }
        }
    }

    fun onCleared(){
        servicePopularMovies.onCleared()
        serviceTopMovies.onCleared()
    }
}