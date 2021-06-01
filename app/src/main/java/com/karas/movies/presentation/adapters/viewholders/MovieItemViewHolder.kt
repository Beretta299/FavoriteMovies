package com.karas.movies.presentation.adapters.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.karas.movies.R
import com.karas.movies.databinding.MovieItemBinding
import com.karas.movies.db.pojo.MovieData
import com.squareup.picasso.Picasso

class MovieItemViewHolder(private val movieItemBinding: MovieItemBinding, private val context: Context) : RecyclerView.ViewHolder(movieItemBinding.root) {

    fun initMovieCard(movieModel: MovieData) {
        movieItemBinding.tvMovieTitle.text = movieModel.title
        movieItemBinding.tvMovieDescription.text = movieModel.description
        movieItemBinding.tvMovieRating.text = String.format(context.resources.getString(R.string.movie_card_rate_holder), movieModel.rank)

        Picasso.get()
            .load(if(movieModel.imagePath.isEmpty()) null else movieModel.imagePath)
            .error(R.drawable.ic_no_image_holder)
            .into(movieItemBinding.ivImageHolder)
    }
}