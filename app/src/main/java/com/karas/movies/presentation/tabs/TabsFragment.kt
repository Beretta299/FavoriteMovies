package com.karas.movies.presentation.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.karas.movies.R
import com.karas.movies.arch.BaseFragment
import com.karas.movies.databinding.MainScreenBinding
import com.karas.movies.presentation.adapters.ViewPagerAdapter
import com.karas.movies.presentation.list.MoviesListFragment
import com.karas.movies.presentation.list.rated.RatedListFragment

class TabsFragment : BaseFragment<MainScreenBinding>() {
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val viewModel: TabsViewModel by viewModels { viewModelFactory }

    companion object {
        private const val MoviesList = 0
        private const val RatedList = 1
    }

    private val tabsScreens = mapOf(
        MoviesList to { MoviesListFragment() },
        RatedList to { RatedListFragment() }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            viewPagerAdapter = ViewPagerAdapter(tabsScreens.values.toList() as List<() -> Fragment>, this@TabsFragment)
            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
                when(pos) {
                    0 -> {
                        tab.text = getString(R.string.movies)
                    }
                    1 -> {
                        tab.text = getString(R.string.favourites)
                    }
                }
            }.attach()

        }
    }

    override fun bind(): MainScreenBinding {
        return MainScreenBinding.inflate(layoutInflater)
    }
}