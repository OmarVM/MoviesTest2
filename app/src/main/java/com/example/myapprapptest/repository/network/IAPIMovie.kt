package com.example.myapprapptest.repository.network

import com.example.myapprapptest.models.MovieJSONResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IAPIMovie {

    @GET("movie/{typeContent}")
    fun getMoviesList(@Path("typeContent") typeContent : String,
                        @Query(ConstantServer.KEY_PAGE) page: String,
                        @Query(ConstantServer.KEY_LANGUAGE) language : String,
                        @Query(ConstantServer.KEY_API) keyApi: String) : Single<MovieJSONResponse>
}