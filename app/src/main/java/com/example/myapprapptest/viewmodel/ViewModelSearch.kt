package com.example.myapprapptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapprapptest.models.Movie
import com.example.myapprapptest.repository.RepositoryMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelSearch : ViewModel() {

    @Inject
    lateinit var getAllMovies: RepositoryMovie
    var mListMoviesLD = MutableLiveData<List<Movie>>()

    fun getData(){
        val getData = viewModelScope.launch(Dispatchers.IO) {
            mListMoviesLD.postValue(getAllMovies.getTopMoviesRepo())
        }
    }

    fun searchMovie(list: List<Movie>, str: String){
        val formatText = str.toLowerCase()
        val listResult = arrayListOf<Movie>()
        list.forEach {
            val title = it.title.toLowerCase()
            if (title.contains(formatText)){
                listResult.add(it)
            }
        }
        mListMoviesLD.postValue(listResult)
    }
}