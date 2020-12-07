package com.example.jetpackprosubmission.ui.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteMovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.di.Injection
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.example.jetpackprosubmission.util.Helper.runtimeFormatting
import com.example.jetpackprosubmission.vo.Status
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_FAVORITE = "extra_movie_favorite"
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var viewModel: MovieViewModel
    lateinit var movieFavorite: MovieEntity
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(activity_detail_movie_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        )[MovieViewModel::class.java]

        activity_detail_movie_progressBar_layout.visibility = View.VISIBLE

        val extras = intent.extras
        if (extras != null) {
            val extraMovie = extras.getParcelable<MovieEntity>(EXTRA_MOVIE)
            val extraMovieFavorite = extras.getParcelable<FavoriteMovieEntity>(EXTRA_MOVIE_FAVORITE)

            if (extraMovieFavorite != null) {
                movieFavorite = convertFavorite(extraMovieFavorite)
            } else if (extraMovie != null) movieFavorite = extraMovie

            viewModel.setMovieId(movieFavorite.id)
            viewModel.checkFavoriteMovie()?.observe(this, { state ->
                isFavorite = state != null
                setData(viewModel)
            })
        }
    }

    private fun setData(viewModel: MovieViewModel) {
        if (isFavorite) {
            activity_detail_movie_progressBar_layout.visibility = View.GONE
            activity_detail_movie_fab_favorite.setImageResource(R.drawable.ic_favorite_fill)
            activity_detail_movie_fab_favorite.setOnClickListener {
                viewModel.deleteFavoriteMovie(movieFavorite)
            }
            loadData(movieFavorite)
        } else {
            activity_detail_movie_fab_favorite.setImageResource(R.drawable.ic_favorite_empty)
            viewModel.setMovieId(movieFavorite.id)
            viewModel.getMovieDetail().observe(this, { movie ->
                if (movie != null) {
                    if (movie.status == Status.SUCCESS) {
                        activity_detail_movie_progressBar_layout.visibility = View.GONE
                        if (movie.data != null) {
                            movieFavorite = movie.data!!
                            activity_detail_movie_fab_favorite.setOnClickListener {
                                viewModel.insertFavoriteMovie(movieFavorite)
                            }
                            loadData(movieFavorite)
                        }
                    } else if (movie.status == Status.ERROR) {
                        activity_detail_movie_progressBar_layout.visibility = View.GONE
                        Toast.makeText(
                            this,
                            resources.getString(R.string.error_message),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })
        }
    }

    private fun loadData(movieEntity: MovieEntity?) {
        movieEntity?.let {
            Picasso.get().load(IMAGE_URL + it.posterPath).fit()
                .placeholder(R.drawable.loading_decor).error(R.drawable.ic_error)
                .into(activity_detail_movie_iv_poster)
            activity_detail_movie_tv_title.text = it.title
            activity_detail_movie_tv_rating.text = it.voteAverage
            activity_detail_movie_tv_runtime.text = it.runtime?.let { runtimeFormatting(it) }
            activity_detail_movie_tv_overview.text = it.overview
            activity_detail_movie_tv_releasedate.text = it.releaseDate
            activity_detail_movie_tv_genre.text = it.genres
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

    private fun convertFavorite(movie: FavoriteMovieEntity): MovieEntity {
        return MovieEntity(
            id = movie.id,
            title = movie.title,
            releaseDate = movie.releaseDate,
            overview = movie.overview,
            posterPath = movie.posterPath,
            runtime = movie.runtime,
            voteAverage = movie.voteAverage,
            genres = movie.genres
        )
    }
}
