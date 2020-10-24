package com.example.jetpackprosubmission.ui.view.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.data.model.MovieEntity
import com.example.jetpackprosubmission.data.viewmodel.MainViewModel
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.example.jetpackprosubmission.util.ConstantValue.REQUEST_INTERVAL_TIME
import com.example.jetpackprosubmission.util.ConstantValue.WARNING_TIME
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_movie_detail.*
import kotlinx.android.synthetic.main.error_warning_layout.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class MovieDetailActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(activity_detail_movie_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showWarning(false)
        showLoading(true)

        val movie = intent.getParcelableExtra<MovieEntity>("movie")

        setData(movie)
    }

    private fun setData(movie: MovieEntity?) {
        mainViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory(application)
            ).get(
                MainViewModel::class.java
            )
        mainViewModel.setMovieDetail(movie?.id)
        getData()
    }

    private fun getData() {
        if (mainViewModel.getErrorMessage().isNotEmpty()) {
            if (mainViewModel.getErrorMessage() == "success") {
                if (mainViewModel.getMovieDetail().title != null) {
                    val movie: MovieEntity = mainViewModel.getMovieDetail()
                    loadData(movie)
                } else {
                    Handler().postDelayed({
                        getData()
                    }, REQUEST_INTERVAL_TIME)
                }
            } else {
                showLoading(false)
                showWarning(true)
                Handler().postDelayed({
                    showWarning(false)
                }, WARNING_TIME)
            }
        } else {
            Handler().postDelayed({
                getData()
            }, REQUEST_INTERVAL_TIME)
        }
    }

    private fun loadData(movie: MovieEntity?) {
        movie?.let {
            Picasso.get().load(IMAGE_URL + it.poster_path).fit()
                .placeholder(R.drawable.loading_decor).error(R.drawable.ic_imageerror)
                .into(activity_detail_movie_iv_poster)
            activity_detail_movie_tv_title.text = it.title
            activity_detail_movie_tv_rating.text = it.vote_average
            activity_detail_movie_tv_runtime.text = getString(R.string.minutes, it.runtime)
            activity_detail_movie_tv_overview.text = it.overview
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                activity_detail_movie_tv_releasedate.text =
                    LocalDateTime.parse(it.release_date).toLocalDate().format(
                        DateTimeFormatter.ofLocalizedDate(
                            FormatStyle.LONG
                        ).withLocale(Locale.US)
                    )
            } else {
                activity_detail_movie_tv_releasedate.text = it.release_date
            }
            val listGenres = ArrayList<String?>()
            it.genres?.forEach { genre ->
                listGenres.add(genre.name)
            }
            activity_detail_movie_tv_genre.text = listGenres.joinToString(separator = ", ")
            activity_detail_movie_ib_share.setOnClickListener { onShareClick(movie) }
            showLoading(false)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            activity_detail_movie_progressBar_layout.visibility = View.VISIBLE
        } else {
            activity_detail_movie_progressBar_layout.visibility = View.GONE
        }
    }

    private fun showWarning(state: Boolean) {
        if (state) {
            activity_detail_movie_warning_layout.visibility = View.VISIBLE
            error_warning_tv.text = mainViewModel.getErrorMessage()
        } else {
            activity_detail_movie_warning_layout.visibility = View.GONE
            error_warning_tv.text = ""
        }
    }

    private fun onShareClick(movie: MovieEntity?) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share")
            .setText("I recommend you to watch ${movie?.title}")
            .startChooser()

    }

}
