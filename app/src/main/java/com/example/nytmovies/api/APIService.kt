package com.example.nytmovies.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object APIService {

    val service: NYTMoviesServices = initRetrofit().create(NYTMoviesServices::class.java)

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/movies/v2/critics/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}