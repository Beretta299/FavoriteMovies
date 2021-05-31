package com.karas.movies.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karas.movies.databinding.MovieItemBinding
import com.karas.movies.presentation.adapters.utils.MoviesDiffUtil
import com.karas.movies.presentation.adapters.viewholders.MovieItemViewHolder
import com.karas.movies.presentation.data.pojo.MovieModel

class MoviesListAdapter(private val context: Context) : RecyclerView.Adapter<MovieItemViewHolder>(){

    private var movies = arrayListOf<MovieModel>()
    private lateinit var binding: MovieItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        binding = MovieItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieItemViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.initMovieCard(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateList(newMoviesList: List<MovieModel>) {
        val moviesDiffUtil = MoviesDiffUtil(movies, newMoviesList)
        val diffResult = DiffUtil.calculateDiff(moviesDiffUtil)
        this.movies.clear()
        this.movies.addAll(newMoviesList)
        diffResult.dispatchUpdatesTo(this)
    }
}