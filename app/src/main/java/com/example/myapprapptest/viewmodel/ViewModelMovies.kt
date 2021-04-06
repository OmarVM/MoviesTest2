package com.example.myapprapptest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.repository.RepositoryMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelMovies : ViewModel() {

    @Inject
    lateinit var getTopMovies: RepositoryMovie

    private val _mListTop = MutableLiveData<List<Movie>>()
    var mListTop: LiveData<List<Movie>> = _mListTop

    private val _mListPop = MutableLiveData<List<Movie>>()
    var mListPop: LiveData<List<Movie>> = _mListPop

    @ExperimentalCoroutinesApi
    fun getData(){
       viewModelScope.launch(Dispatchers.IO) {
           getTopMovies.getTopMoviesRepo().collect {
               _mListTop.postValue(it)
           }
        }
    }


    @ExperimentalCoroutinesApi
    fun getDataPopular(){
        viewModelScope.launch(Dispatchers.IO) {
            getTopMovies.getPopularMoviesRepo().collect {
                _mListPop.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        getTopMovies.onCleared()
    }
}