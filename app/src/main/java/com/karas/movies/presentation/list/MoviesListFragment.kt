package com.karas.movies.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.karas.movies.arch.BaseFragment
import com.karas.movies.databinding.MovieScreenBinding
import com.karas.movies.pojo.MovieData
import com.karas.movies.presentation.adapters.MoviesListAdapter
import com.karas.movies.presentation.adapters.utils.RateMovieListener
import com.karas.movies.presentation.utils.*

class MoviesListFragment : BaseFragment<MovieScreenBinding>(),
    SwipeRefreshLayout.OnRefreshListener, RateMovieListener {

    private val viewModel: MoviesListViewModel by viewModels { viewModelFactory }
    private lateinit var moviesListAdapter :MoviesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
    }

    override fun bind(): MovieScreenBinding {
        return MovieScreenBinding.inflate(layoutInflater)
    }

    private fun initViews() {
        with(binding) {
            moviesListAdapter = MoviesListAdapter(requireContext(), this@MoviesListFragment)
            val layoutManager = LinearLayoutManager(requireContext())
            rvMoviesList.adapter = moviesListAdapter
            rvMoviesList.layoutManager = layoutManager
            srlSwipeLayout.setOnRefreshListener(this@MoviesListFragment)
            rvMoviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    val manager = rvMoviesList.layoutManager as LinearLayoutManager
                    viewModel.checkIsAutoScrollNeeded(manager.findLastVisibleItemPosition())
                }
            })
        }
    }

    private fun initObservers() {
        viewModel.getMoviesLiveData().observe(viewLifecycleOwner, { moviesList ->
            moviesListAdapter.updateList(moviesList.first, moviesList.second)
        })
        viewModel.getUIStages().observe(viewLifecycleOwner, { uiStages ->
            with(binding) {
                when(uiStages) {
                    is Loading -> {
                        pbProgress.visibility = View.VISIBLE
                    }
                    is Loaded -> {
                        srlSwipeLayout.isRefreshing = false
                        pbProgress.visibility = View.INVISIBLE
                    }
                }
            }
        })
    }

    override fun onRefresh() {
        viewModel.refreshList()
    }

    override fun rateMovie(movieData: MovieData) {
        viewModel.rateMovie(movieData)
    }
}