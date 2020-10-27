package com.example.jetpackprosubmission.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.data.MovieEntity
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_movie_detail.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(activity_detail_movie_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                loadData(viewModel.getMovie())
            }
        }
    }

    private fun loadData(movieEntity: MovieEntity?) {
        movieEntity?.let {
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
            activity_detail_movie_ib_share.setOnClickListener { onShareClick(movieEntity) }
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
