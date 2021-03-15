package com.example.myapprapptest.repository

import android.util.Log
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.repository.database.DaoMovies
import com.example.myapprapptest.usecases.NetworkPopularListMoviesImpl
import com.example.myapprapptest.usecases.NetworkTopListMoviesImpl
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class RepositoryMovie @Inject constructor(private val mDao: DaoMovies,
                                          private val serviceTopMovies: NetworkTopListMoviesImpl,
                                          private val servicePopularMovies: NetworkPopularListMoviesImpl) : ICallbackNetworkOperation{

    suspend fun getTopMoviesRepo(): List<Movie>{
        if (mDao.getTopMovies().isNotEmpty()){
            Log.d("OVM", "fromDB")
            return mDao.getTopMovies()
        }
        serviceTopMovies.setCallbackOperation(this)
        return serviceTopMovies.getInfo()
    }

    override fun requestSuccess(movies: List<Movie>) {
        movies.let {
            runBlocking {
                val insertResult = mDao.insertMovie(it)
                Log.d("OVM", "Value Saved : ${insertResult.size}")
            }
        }
    }

    override fun requestError(msn: String) {
        TODO("Not yet implemented")
    }

    suspend fun getPopularMoviesRepo(): List<Movie>{
        if (mDao.getPopularMovies().isNotEmpty()){
            Log.d("OVM", "Popular fromDB")
            return mDao.getPopularMovies()
        }

        servicePopularMovies.setCallbackOperation(this)
        return servicePopularMovies.getInfo()
    }

    override fun requestSuccessPopular(movies: List<Movie>) {
        movies.let {
            runBlocking {
                val insertPopular = mDao.insertMovie(it)
                Log.d("OVM", "Value Popular Saved : ${insertPopular.size}")
            }
        }
    }

    override fun requestErrorPopular(msn: String) {
        TODO("Not yet implemented")
    }
}