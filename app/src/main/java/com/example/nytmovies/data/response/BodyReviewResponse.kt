package com.example.nytmovies.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BodyReviewResponse (
    val results: List<ReviewResponse>
)