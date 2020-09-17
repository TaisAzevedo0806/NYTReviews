package com.example.nytmovies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.nytmovies.R
import com.example.nytmovies.data.model.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(
    private val reviewList: List<Review>,
    val onItemClickListener: ((review: Review) -> Unit)
) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bindView(reviewList[position])
    }

    override fun getItemCount() = reviewList.size

    class ReviewViewHolder(
        itemView: View,
        val onItemClickListener: (review: Review) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.tvName
        private val bio = itemView.tvBio
        private val status = itemView.tvStatus

        fun bindView(review: Review) {
            name.text = review.name
            bio.text = review.bio
            status.text = review.status

            itemView.setOnClickListener { onItemClickListener.invoke(review) }
        }
    }
}