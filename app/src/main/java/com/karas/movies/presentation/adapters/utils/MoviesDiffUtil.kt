package com.karas.movies.presentation.adapters.utils

import androidx.recyclerview.widget.DiffUtil
import com.karas.movies.pojo.MovieData

class MoviesDiffUtil(private val oldList:List<MovieData>, private val newList: List<MovieData>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].imagePath == newList[newItemPosition].imagePath
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title &&
                oldList[oldItemPosition].description == newList[newItemPosition].description &&
                oldList[oldItemPosition].imagePath == newList[newItemPosition].imagePath &&
                oldList[oldItemPosition].rank == newList[newItemPosition].rank&&
                oldList[oldItemPosition].isLiked == newList[newItemPosition].isLiked
    }
}