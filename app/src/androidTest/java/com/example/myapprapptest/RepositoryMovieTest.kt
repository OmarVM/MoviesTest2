package com.example.myapprapptest

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.repository.RepositoryMovie
import com.example.myapprapptest.repository.database.DBMovieHelper
import com.example.myapprapptest.repository.database.DaoMovies
import com.example.myapprapptest.usecases.NetworkPopularListMoviesImpl
import com.example.myapprapptest.usecases.NetworkTopListMoviesImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class RepositoryMovieTest {
    private lateinit var mDao: DaoMovies
    private lateinit var dbHelper: DBMovieHelper

    var serviceTopMovies: NetworkTopListMoviesImpl = Mockito.mock(NetworkTopListMoviesImpl::class.java)

    var servicePopuMovies: NetworkPopularListMoviesImpl = Mockito.mock(NetworkPopularListMoviesImpl::class.java)

    lateinit var sut: RepositoryMovie

    @Before
    fun setup(){
        val mContext = InstrumentationRegistry.getInstrumentation().context
        dbHelper = Room.inMemoryDatabaseBuilder(mContext, DBMovieHelper::class.java).build()
        mDao = dbHelper.daoItem()
    }

    @Test
    fun returnEmptyList() = runBlocking{
        sut = RepositoryMovie(mDao, serviceTopMovies, servicePopuMovies)
        val listEmpty = listOf<Movie>()
        Mockito.`when`(mDao.getAllContent()).thenReturn(listEmpty)
        val allMovies = sut.getAllMoviesCache()
        Assert.assertEquals(0, allMovies.size)
    }

}