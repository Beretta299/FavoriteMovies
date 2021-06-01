package com.karas.movies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karas.movies.R
import com.karas.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
    }

    private fun initObservers() {

    }
}