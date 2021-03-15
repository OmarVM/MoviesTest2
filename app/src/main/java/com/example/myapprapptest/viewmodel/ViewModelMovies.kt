package com.example.myapprapptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.repository.RepositoryMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelMovies : ViewModel() {

    @Inject
    lateinit var getTopMovies: RepositoryMovie

    var mListTop =  MutableLiveData<List<Movie>>()
    var mListPop = MutableLiveData<List<Movie>>()

    fun getData(){
        val getData = viewModelScope.launch(Dispatchers.IO) {
            mListTop.postValue(getTopMovies.getTopMoviesRepo())
        }
    }

    fun getDataPopular(){
        val getData = viewModelScope.launch(Dispatchers.IO) {
            mListPop.postValue(getTopMovies.getPopularMoviesRepo())
        }
    }
}