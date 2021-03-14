package com.example.myapprapptest.repository

import androidx.lifecycle.LiveData
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.repository.database.DaoMovies
import com.example.myapprapptest.usecases.NetworkTopListMoviesImpl
import javax.inject.Inject

class RepositoryMovie @Inject constructor(  val mDao: DaoMovies,
                                            val serviceTopMovies: NetworkTopListMoviesImpl) {

    suspend fun getTopMoviesRepo(): LiveData<List<Movie>>{
        if (mDao.getTopMovies().isNotEmpty()){
            return mDao.getTopMovies() as LiveData<List<Movie>>
        }
        return serviceTopMovies.getInfo()
    }

}