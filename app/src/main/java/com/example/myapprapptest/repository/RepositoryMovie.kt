package com.example.myapprapptest.repository

import android.util.Log
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.repository.database.DaoMovies
import com.example.myapprapptest.usecases.NetworkPopularListMoviesImpl
import com.example.myapprapptest.usecases.NetworkTopListMoviesImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class RepositoryMovie @Inject constructor(private val mDao: DaoMovies,
                                          private val serviceTopMovies: NetworkTopListMoviesImpl,
                                          private val servicePopularMovies: NetworkPopularListMoviesImpl) : ICallbackNetworkOperation{

    suspend fun getAllMoviesCache(): List<Movie>{
        if (mDao.getAllContent().isNotEmpty()){
            Log.d("OVM", "All movies fromDB")
            return mDao.getAllContent()
        }
        return arrayListOf()
    }

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
                launch(Dispatchers.IO) {
                    val insertPopular = mDao.insertMovie(it)
                    Log.d("OVM", "Value Popular Saved : ${insertPopular.size}")
                }
            }
        }
    }

    override fun requestErrorPopular(msn: String) {
        TODO("Not yet implemented")
    }

    fun onCleared(){
        servicePopularMovies.onCleared()
        serviceTopMovies.onCleared()
    }
}