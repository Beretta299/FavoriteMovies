package com.karas.movies.presentation.list.rated

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.karas.movies.arch.BaseFragment
import com.karas.movies.databinding.RatedScreenBinding
import com.karas.movies.pojo.MovieData
import com.karas.movies.presentation.adapters.MoviesListAdapter
import com.karas.movies.presentation.adapters.utils.RateMovieListener
import com.karas.movies.presentation.utils.Loaded
import com.karas.movies.presentation.utils.Loading

class RatedListFragment : BaseFragment<RatedScreenBinding>(),
    SwipeRefreshLayout.OnRefreshListener, RateMovieListener {
    private val viewModel: RatedListViewModel by viewModels { viewModelFactory }
    private lateinit var moviesListAdapter :MoviesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }

    override fun bind(): RatedScreenBinding {
        return RatedScreenBinding.inflate(layoutInflater)
    }

    private fun initViews() {
        with(binding) {
            moviesListAdapter = MoviesListAdapter(requireContext(), this@RatedListFragment)
            val layoutManager = LinearLayoutManager(requireContext())
            rvMoviesList.adapter = moviesListAdapter
            rvMoviesList.layoutManager = layoutManager
            srlSwipeLayout.setOnRefreshListener(this@RatedListFragment)
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
        viewModel.refreshData()
    }

    override fun rateMovie(movieData: MovieData) {
        viewModel.rateMovie(movieData)
    }
}