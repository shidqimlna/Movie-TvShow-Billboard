package com.example.jetpackprosubmission.view.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.adapter.TvShowAdapter
import com.example.jetpackprosubmission.util.ConstantValue.REQUEST_INTERVAL_TIME
import com.example.jetpackprosubmission.util.ConstantValue.WARNING_TIME
import com.example.jetpackprosubmission.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.error_warning_layout.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {
    private val tvShowAdapter: TvShowAdapter = TvShowAdapter()
    private lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showWarning(false)
        showLoading(true)

        tvShowAdapter.notifyDataSetChanged()

        fragment_movie_recyclerView.setHasFixedSize(true)
        fragment_movie_recyclerView.layoutManager = GridLayoutManager(context, 2)
        fragment_movie_recyclerView.adapter = tvShowAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            mainViewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory(it.application)
            ).get(MainViewModel::class.java)
        }
        setData()
    }

    private fun setData() {
        mainViewModel.setTvShowList()
        loadData()
    }

    private fun loadData() {
        if (mainViewModel.getErrorMessage().isNotEmpty()) {
            if (mainViewModel.getErrorMessage() == "success") {
                mainViewModel.getTvShowList().observe(this, Observer { resultItems ->
                    if (resultItems != null) {
                        tvShowAdapter.setData(resultItems)
                        showLoading(false)
                    } else {
                        Handler().postDelayed({
                            loadData()
                        }, REQUEST_INTERVAL_TIME)
                    }
                })
            } else {
                showLoading(false)
                showWarning(true)
                Handler().postDelayed({
                    showWarning(false)
                }, WARNING_TIME)
            }
        } else {
            Handler().postDelayed({
                loadData()
            }, REQUEST_INTERVAL_TIME)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragment_tvShow_progressBar_layout.visibility = View.VISIBLE
        } else {
            fragment_tvShow_progressBar_layout.visibility = View.GONE
        }
    }

    private fun showWarning(state: Boolean) {
        if (state) {
            fragment_tvShow_progressBar_layout.visibility = View.VISIBLE
            error_warning_tv.text = mainViewModel.getErrorMessage()
        } else {
            fragment_tvShow_progressBar_layout.visibility = View.GONE
            error_warning_tv.text = ""
        }
    }

}