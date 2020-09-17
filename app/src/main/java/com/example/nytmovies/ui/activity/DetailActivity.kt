package com.example.nytmovies.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nytmovies.R
import com.example.nytmovies.base.BaseActivity
import com.example.nytmovies.data.model.Review
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlinx.android.synthetic.main.item_review.*

class DetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupToolbar(mainToolbar, R.string.review_detail_title, true)
        setValues()
    }

    private fun setValues() {
        tvName.text = intent.getStringExtra(EXTRA_NAME)
        tvBio.text = intent.getStringExtra(EXTRA_BIO)
        tvStatus.text = intent.getStringExtra(EXTRA_STATUS)
    }

    companion object {
        private const val EXTRA_NAME = "name"
        private const val EXTRA_BIO = "bio"
        private const val EXTRA_STATUS = "status"

        fun getStartIntent(context: Context, review: Review): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_NAME, review.name)
                putExtra(EXTRA_BIO, review.bio)
                putExtra(EXTRA_STATUS, review.status)
            }
        }
    }
}