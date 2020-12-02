package com.example.jetpackprosubmission.ui.favorite.tvshow

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
import kotlinx.android.synthetic.main.fragment_fav_tv_show.*

class FavTvShowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val viewModel = ViewModelProvider(
                requireActivity(),
                Injection.provideViewModelFactory(requireContext())
            )[FavoriteViewModel::class.java]

            val tvShowAdapter = FavTvShowAdapter()

            fragment_fav_tvShow_progress_bar.visibility = View.VISIBLE

            viewModel.getTvShowList().observe(this, { tvShow ->
                fragment_fav_tvShow_progress_bar.visibility = View.GONE
                tvShowAdapter.submitList(tvShow)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(fragment_fav_tvShow_recyclerView) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = tvShowAdapter
            }
        }
    }
}