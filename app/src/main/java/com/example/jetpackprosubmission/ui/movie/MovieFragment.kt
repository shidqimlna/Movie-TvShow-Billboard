package com.example.jetpackprosubmission.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.di.Injection
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val viewModel = ViewModelProvider(
                this,
                Injection.provideViewModelFactory()
            )[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()

            fragment_movie_progress_bar.visibility = View.VISIBLE

            viewModel.getMovieList().observe(this, { movies ->
                fragment_movie_progress_bar.visibility = View.GONE
                movieAdapter.setData(movies)
                movieAdapter.notifyDataSetChanged()
            })

            fragment_movie_recyclerView.setHasFixedSize(true)
            fragment_movie_recyclerView.layoutManager = LinearLayoutManager(context)
            fragment_movie_recyclerView.adapter = movieAdapter
        }
    }
}