package com.example.jetpackprosubmission.ui.favorite.favmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.di.Injection
import com.example.jetpackprosubmission.ui.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_fav_movie.*

class FavMovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val viewModel = ViewModelProvider(
                requireActivity(),
                Injection.provideViewModelFactory(requireContext())
            )[FavoriteViewModel::class.java]

            val movieAdapter = FavMovieAdapter()

            fragment_fav_movie_progress_bar.visibility = View.VISIBLE

            viewModel.getMovieList().observe(this, { movies ->
                fragment_fav_movie_progress_bar.visibility = View.GONE
                movieAdapter.submitList(movies)
                movieAdapter.notifyDataSetChanged()
            })

            with(fragment_fav_movie_recyclerView) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }
        }
    }
}