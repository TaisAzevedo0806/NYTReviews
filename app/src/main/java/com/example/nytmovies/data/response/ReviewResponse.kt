package com.example.nytmovies.data.response

import com.example.nytmovies.data.model.Review
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResponse(
    @Json(name = "display_name")
    val name: String,
    val bio: String?,
    val status: String?
) {

    fun getReviewModel(): Review {
        return Review(
            name = this.name,
            bio = this.bio,
            status = this.status
        )
    }

}