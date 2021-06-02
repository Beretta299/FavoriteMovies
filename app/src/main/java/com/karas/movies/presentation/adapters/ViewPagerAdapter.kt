package com.karas.movies.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    private val fragmentScreens: List<() ->Fragment>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return fragmentScreens.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentScreens[position].invoke()
    }
}