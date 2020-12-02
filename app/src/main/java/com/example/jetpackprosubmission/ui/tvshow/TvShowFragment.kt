package com.example.jetpackprosubmission.ui.tvshow

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
                Injection.provideViewModelFactory(requireContext())
            )[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()

            viewModel.getTvShowList().observe(this, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> fragment_tvShow_progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragment_tvShow_progress_bar.visibility = View.GONE
                            tvShowAdapter.submitList(tvShow.data)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragment_tvShow_progress_bar.visibility = View.GONE
                            Toast.makeText(
                                context,
                                resources.getString(R.string.error_message),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            })

            with(fragment_tvShow_recyclerView) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = tvShowAdapter
            }

//            fragment_tvShow_recyclerView.setHasFixedSize(true)
//            fragment_tvShow_recyclerView.layoutManager = LinearLayoutManager(context)
//            fragment_tvShow_recyclerView.adapter = tvShowAdapter
        }
    }
}