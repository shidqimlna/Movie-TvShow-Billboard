package com.example.jetpackprosubmission.ui.view.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.data.model.TvShowEntity
import com.example.jetpackprosubmission.data.viewmodel.MainViewModel
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.example.jetpackprosubmission.util.ConstantValue.REQUEST_INTERVAL_TIME
import com.example.jetpackprosubmission.util.ConstantValue.WARNING_TIME
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tvshow.*
import kotlinx.android.synthetic.main.content_tvshow_detail.*
import kotlinx.android.synthetic.main.error_warning_layout.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class TvShowDetailActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)
        setSupportActionBar(activity_detail_tvshow_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showWarning(false)
        showLoading(true)

        val tvShow = intent.getParcelableExtra<TvShowEntity>("tvShow")

        setData(tvShow)
    }

    private fun setData(tvShow: TvShowEntity?) {
        mainViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory(application)
            ).get(
                MainViewModel::class.java
            )
        mainViewModel.setTvShowDetail(tvShow?.id)
        getData()

    }

    private fun getData() {
        if (mainViewModel.getErrorMessage().isNotEmpty()) {
            if (mainViewModel.getErrorMessage() == "success") {
                if (mainViewModel.getTvShowDetail().name != null) {
                    val tvShow: TvShowEntity = mainViewModel.getTvShowDetail()
                    loadData(tvShow)
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

    private fun loadData(tvShow: TvShowEntity?) {
        tvShow?.let {
            Picasso.get().load(IMAGE_URL + it.poster_path).fit()
                .placeholder(R.drawable.loading_decor).error(R.drawable.ic_imageerror)
                .into(activity_detail_tvshow_iv_poster)
            activity_detail_tvshow_tv_title.text = it.name
            activity_detail_tvshow_tv_rating.text = it.vote_average
            activity_detail_tvshow_tv_seasons.text = it.number_of_seasons
            activity_detail_tvshow_tv_episodes.text = it.number_of_episodes
            activity_detail_tvshow_tv_overview.text = it.overview
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                activity_detail_tvshow_tv_firstairdate.text =
                    LocalDateTime.parse(it.first_air_date).toLocalDate().format(
                        DateTimeFormatter.ofLocalizedDate(
                            FormatStyle.LONG
                        ).withLocale(Locale.US)
                    )
                activity_detail_tvshow_tv_lastairdate.text =
                    LocalDateTime.parse(it.last_air_date).toLocalDate().format(
                        DateTimeFormatter.ofLocalizedDate(
                            FormatStyle.LONG
                        ).withLocale(Locale.US)
                    )
            } else {
                activity_detail_tvshow_tv_firstairdate.text = it.first_air_date
                activity_detail_tvshow_tv_lastairdate.text = it.last_air_date
            }
            val listGenres = ArrayList<String?>()
            it.genres?.forEach { genre ->
                listGenres.add(genre.name)
            }
            activity_detail_tvshow_tv_genre.text = listGenres.joinToString(separator = ", ")
            activity_detail_tvshow_ib_share.setOnClickListener { onShareClick(tvShow) }
            showLoading(false)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            activity_detail_tvshow_progressBar_layout.visibility = View.VISIBLE
        } else {
            activity_detail_tvshow_progressBar_layout.visibility = View.GONE
        }
    }

    private fun showWarning(state: Boolean) {
        if (state) {
            activity_detail_tvshow_warning_layout.visibility = View.VISIBLE
            error_warning_tv.text = mainViewModel.getErrorMessage()
        } else {
            activity_detail_tvshow_warning_layout.visibility = View.GONE
            error_warning_tv.text = ""
        }
    }

    private fun onShareClick(tvShow: TvShowEntity?) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share")
            .setText("I recommend you to watch ${tvShow?.name}")
            .startChooser()

    }

}
