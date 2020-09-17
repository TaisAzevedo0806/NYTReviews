package com.example.nytmovies.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nytmovies.R
import com.example.nytmovies.api.APIService
import com.example.nytmovies.data.model.Review
import com.example.nytmovies.data.response.BodyReviewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewViewModel : ViewModel() {

    val reviewListLiveData: MutableLiveData<List<Review>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getReviews() {
        APIService.service.getReviews().enqueue(object : Callback<BodyReviewResponse> {

            override fun onResponse(
                call: Call<BodyReviewResponse>,
                response: Response<BodyReviewResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val reviews: MutableList<Review> = mutableListOf()
                        response.body()?.let { for (result in it.results) reviews.add(result.getReviewModel()) }

                        reviewListLiveData.value = reviews
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_REVIEWS, null)
                    }
                    response.code() == 401 -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_401)
                    else -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_unknown)
                }
            }

            override fun onFailure(call: Call<BodyReviewResponse>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_service)
            }

        })
    }

    companion object {
        private const val VIEW_FLIPPER_REVIEWS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

  /*  private fun createReviewList(): List<Review> {
        return listOf(
            Review("Name 1", "Bio 1", "Status 1"),
            Review("Name 2", "Bio 2", "Status 2"),
            Review("Name 3", "Bio 3", "Status 3"),
            Review("Name 4", "Bio 4", "Status 4"),
            Review("Name 5", "Bio 5", "Status 5")
        )
    }*/
}