package com.example.myapprapptest.repository

import com.example.myapprapptest.models.Movie

interface ICallbackNetworkOperation {

    fun requestSuccessPopular(movies: List<Movie>)
    fun requestErrorPopular(msn: String)
}