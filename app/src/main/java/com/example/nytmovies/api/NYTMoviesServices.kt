package com.example.nytmovies.api

import com.example.nytmovies.data.response.BodyReviewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTMoviesServices {

    @GET("all.json")
    fun getReviews(
        @Query("api-key") apiKey: String = "wF7vo4RzHegWnU4YdLZWTZV88I98ylWT"
    ): Call<BodyReviewResponse>

}