package com.example.nytmovies.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytmovies.R
import com.example.nytmovies.base.BaseActivity
import com.example.nytmovies.ui.adapter.ReviewAdapter
import com.example.nytmovies.ui.viewModel.ReviewViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar(mainToolbar, R.string.main_title, false)

        configureRecycler()
        setObservers()
    }

    private fun configureRecycler() {
        recyclerReviews.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        recyclerReviews.setHasFixedSize(true)
    }

    private fun setObservers() {
        val reviewViewModel = ReviewViewModel()

        reviewViewModel.reviewListLiveData.observe(this, {
            it?.let { list ->
                recyclerReviews.adapter = ReviewAdapter(list) { review ->
                    val intent = DetailActivity.getStartIntent(this@MainActivity, review)
                    this@MainActivity.startActivity(intent)
                }
            }
        })

        reviewViewModel.viewFlipperLiveData.observe(this, {
            mainViewFlipper.displayedChild = it.first

            it.second?.let { tvError.text = getString(it) }
        })

        reviewViewModel.getReviews()
    }
}