package com.karas.movies.arch

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.karas.movies.App
import com.karas.movies.di.factory.ViewModelFactory
import javax.inject.Inject

abstract class DependenciesLoaderFragment : Fragment() {

    @Inject protected lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent?.inject(this)
    }
}