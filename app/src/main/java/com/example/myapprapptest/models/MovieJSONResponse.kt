package com.example.myapprapptest.models

data class MovieJSONResponse(
    var page: Int,
    var total_results: Int,
    var total_pages: Int,
    var results: List<Movie>
)
