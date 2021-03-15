package com.example.myapprapptest.repository

import com.example.myapprapptest.models.Movie

interface ICallbackNetworkOperation {

    fun requestSuccess(movies: List<Movie>)
    fun requestError(msn: String)

    fun requestSuccessPopular(movies: List<Movie>)
    fun requestErrorPopular(msn: String)
}