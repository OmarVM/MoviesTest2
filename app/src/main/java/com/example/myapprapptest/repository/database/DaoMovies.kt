package com.example.myapprapptest.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.utils.Constants

@Dao
interface DaoMovies {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(vararg movie: Movie): List<Long>

    @Query("SELECT * FROM ${Constants.TABLE_NAME_MOVIES} WHERE top_rated == 1")
    suspend fun getTopMovies() : List<Movie>
}