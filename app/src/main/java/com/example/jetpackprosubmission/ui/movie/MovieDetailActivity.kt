package com.example.jetpackprosubmission.ui.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.di.Injection
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_movie_detail.*
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
            Injection.provideViewModelFactory()
        )[MovieViewModel::class.java]

        activity_detail_movie_progressBar_layout.visibility = View.VISIBLE

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE) ?: ""
            viewModel.setMovie(movieId)
            viewModel.getMovieDetail().observe(this, { movie ->
                activity_detail_movie_progressBar_layout.visibility = View.GONE
                loadData(movie)
            })
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
            activity_detail_movie_tv_releasedate.text = it.release_date

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
            .setText(getString(R.string.share_text, movie?.title))
            .startChooser()

    }

}
