package com.karas.movies.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karas.movies.databinding.MovieItemBinding
import com.karas.movies.db.pojo.MovieData
import com.karas.movies.presentation.adapters.utils.MoviesDiffUtil
import com.karas.movies.presentation.adapters.viewholders.MovieItemViewHolder

class MoviesListAdapter(private val context: Context) : RecyclerView.Adapter<MovieItemViewHolder>(){

    private var movies = arrayListOf<MovieData>()
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

    fun updateList(newMoviesList: List<MovieData>) {
        val newMovies = arrayListOf<MovieData>()
        newMovies.addAll(movies)
        newMovies.addAll(newMoviesList)
        val moviesDiffUtil = MoviesDiffUtil(movies, newMoviesList)
        val diffResult = DiffUtil.calculateDiff(moviesDiffUtil)
        this.movies.clear()
        this.movies.addAll(newMovies)
        diffResult.dispatchUpdatesTo(this)
    }
}