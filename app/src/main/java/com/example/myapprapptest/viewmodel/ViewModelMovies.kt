package com.example.myapprapptest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.repository.RepositoryMovie
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelMovies : ViewModel() {

    @Inject
    lateinit var getTopMovies: RepositoryMovie
    var mListTop: LiveData<List<Movie>> =  MutableLiveData<List<Movie>>()

    fun getData(){
        viewModelScope.launch {
            mListTop = getTopMovies.getTopMoviesRepo()
        }
    }
}