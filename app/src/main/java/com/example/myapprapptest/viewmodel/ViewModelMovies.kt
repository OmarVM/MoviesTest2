package com.example.myapprapptest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.usecases.NetworkTopListMoviesImpl
import javax.inject.Inject

class ViewModelMovies : ViewModel() {

    @Inject
    lateinit var getTopMoviesService: NetworkTopListMoviesImpl

    lateinit var mListTop: LiveData<List<Movie>>

    fun getData(){
        mListTop = getTopMoviesService.getInfo()
    }
}