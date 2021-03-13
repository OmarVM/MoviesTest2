package com.example.myapprapptest.models

import androidx.room.Entity
import com.example.myapprapptest.utils.Constants

@Entity(tableName = Constants.TABLE_NAME_MOVIES)
data class Movie(
    var id: Int,
    var vote_average: Double,
    var title: String,
    var poster_path: String,
    var genre_ids: List<Int>,
    var backdrop_path: String?,
    var overview: String,
    var release_date: String,
    var popular: Int,
    var top_rated: Int,
    var upcoming: Int,)
