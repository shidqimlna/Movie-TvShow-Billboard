package com.example.jetpackprosubmission.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.di.Injection
import com.example.jetpackprosubmission.vo.Status
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
                requireActivity(),
                Injection.provideViewModelFactory(requireContext())
            )[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()

            viewModel.getMovieList().observe(this, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> fragment_movie_progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragment_movie_progress_bar.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragment_movie_progress_bar.visibility = View.GONE
                            Toast.makeText(
                                context,
                                resources.getString(R.string.error_message),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            })

            with(fragment_movie_recyclerView) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }
        }
    }
}