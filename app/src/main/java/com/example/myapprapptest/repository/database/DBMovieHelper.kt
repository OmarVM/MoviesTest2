package com.example.myapprapptest.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.utils.Constants

@Database(entities = [Movie::class], version = Constants.DB_MOVIES_VERSION)
abstract class DBMovieHelper : RoomDatabase() {

    abstract fun daoItem() : DaoMovies

    companion object{
        @Volatile private var instance : DBMovieHelper? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildMDB(context).also {
                instance = it
            }

        }

        private fun buildMDB(context: Context) = Room.databaseBuilder(
            context,
            DBMovieHelper::class.java,
            Constants.RAPPI_MOVIES_DB
        ).build()
    }
}