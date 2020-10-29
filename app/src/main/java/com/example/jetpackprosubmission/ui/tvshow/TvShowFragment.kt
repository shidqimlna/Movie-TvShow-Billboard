package com.example.jetpackprosubmission.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.di.Injection
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val viewModel = ViewModelProvider(
                this,
                Injection.provideViewModelFactory()
            )[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()

            fragment_tvShow_progress_bar.visibility = View.VISIBLE

            viewModel.getTvShowList().observe(this, { tvShow ->
                fragment_tvShow_progress_bar.visibility = View.GONE
                tvShowAdapter.setData(tvShow)
                tvShowAdapter.notifyDataSetChanged()
            })

            fragment_tvShow_recyclerView.setHasFixedSize(true)
            fragment_tvShow_recyclerView.layoutManager = LinearLayoutManager(context)
            fragment_tvShow_recyclerView.adapter = tvShowAdapter
        }
    }
}