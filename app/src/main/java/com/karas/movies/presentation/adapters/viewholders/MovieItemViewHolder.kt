package com.karas.movies.presentation.adapters.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.karas.movies.R
import com.karas.movies.databinding.MovieItemBinding
import com.karas.movies.pojo.MovieData
import com.karas.movies.presentation.adapters.utils.RateMovieListener
import com.squareup.picasso.Picasso

class MovieItemViewHolder(private val movieItemBinding: MovieItemBinding, private val context: Context) : RecyclerView.ViewHolder(movieItemBinding.root) {

    fun initMovieCard(movieModel: MovieData, rateMovieListener: RateMovieListener) {

        with(movieItemBinding) {
            tvMovieTitle.text = movieModel.title
            tvMovieDescription.text = movieModel.description
            tvMovieRating.text = String.format(context.resources.getString(R.string.movie_card_rate_holder), movieModel.rank)

            bFavoriteAction.text = if(movieModel.isLiked) context.getString(R.string.movie_card_unlike_action) else context.getString(R.string.movie_card_like_action)

            bFavoriteAction.setOnClickListener {
                movieModel.isLiked = !movieModel.isLiked
                rateMovieListener.rateMovie(movieModel)
                bFavoriteAction.text = if(movieModel.isLiked) context.getString(R.string.movie_card_unlike_action) else context.getString(R.string.movie_card_like_action)
            }

            Picasso.get()
                .load(if(movieModel.imagePath.isEmpty()) null else movieModel.imagePath)
                .error(R.drawable.ic_no_image_holder)
                .into(ivImageHolder)
        }
    }
}