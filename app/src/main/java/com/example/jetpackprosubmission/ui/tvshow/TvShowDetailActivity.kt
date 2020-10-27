package com.example.jetpackprosubmission.ui.tvshow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.data.TvShowEntity
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tvshow.*
import kotlinx.android.synthetic.main.content_tvshow_detail.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class TvShowDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)
        setSupportActionBar(activity_detail_tvshow_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[TvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_TVSHOW)
            if (movieId != null) {
                viewModel.setSelectedTvShow(movieId)
                loadData(viewModel.getTvShow())
            }
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
