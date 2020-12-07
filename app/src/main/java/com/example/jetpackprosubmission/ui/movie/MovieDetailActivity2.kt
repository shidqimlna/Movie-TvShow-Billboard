package com.example.jetpackprosubmission.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
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

class MovieDetailActivity2 : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_FAVORITE = "extra_movie_favorite"
        const val EXTRA_MOVIE = "extra_movie"
        const val FAVORITED = "isFavorited"
    }

    private lateinit var viewModel: MovieViewModel
    private var isFavorite = false
    private var menu: Menu? = null
    private var isLoading = true

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
//            isFavorite = extras.getBoolean(FAVORITED, false)
            val extraMovie = extras.getParcelable<MovieEntity>(EXTRA_MOVIE)
            val extraMovieFavorite = extras.getParcelable<FavoriteMovieEntity>(EXTRA_MOVIE_FAVORITE)
            lateinit var movieFavorite: MovieEntity
            if (extraMovieFavorite != null) {
                movieFavorite = convertFavorite(extraMovieFavorite)
            } else if (extraMovie != null) movieFavorite = extraMovie
            viewModel.setMovieId(movieFavorite.id)

//            if (extraMovie != null) {
//                viewModel.setMovieId(extraMovie.id)
//                Log.e("Detail Movie", "extraMovie :${extraMovie.title}")
//            }
//            else {
//                viewModel.setMovieId(movieFavorite.id)
//                Log.e("Detail Movie", "movieFavorite :${movieFavorite.title}")
//            }

            viewModel.checkFavorite()?.observe(this, { state ->
                isFavorite = state != null
                setData(movieFavorite, viewModel)
//                when (isFavorite) {
//                    true -> {
//                        Log.e("Detail Movie", "isFavorited True :$isFavorite")
//                        Log.e("Detail Movie", "isFavorited True :" + movieFavorite.title)
//                        setData(movieFavorite, viewModel)
//                    }
//                    false -> {
//                        Log.e("Detail Movie", "isFavorited False :$isFavorite")
//                        if (extraMovie != null) {
//                            Log.e("Detail Movie", "isFavorited False :" + extraMovie.title)
//                            setData(extraMovie, viewModel)
//                        }
//                    }
//                }
            })
            Log.e("Detail Movie", "isFavorited :$isFavorite")

//            when (isFavorite) {
//                true -> {
//                    Log.e("Detail Movie", "isFavorited True :$isFavorite")
//                    Log.e("Detail Movie", "isFavorited True :" + movieFavorite.title)
//                    setData(movieFavorite, viewModel)
//                }
//                false -> {
//                    Log.e("Detail Movie", "isFavorited False :$isFavorite")
//                    if (extraMovie != null) {
//                        Log.e("Detail Movie", "isFavorited False :" + extraMovie.title)
//                        setData(extraMovie, viewModel)
//                    }
//                }
//            }
        }
    }

    private fun setData(movieEntity: MovieEntity, viewModel: MovieViewModel) {
        if (isFavorite) {
            viewModel.setMovieDetail(movieEntity)
            activity_detail_movie_progressBar_layout.visibility = View.GONE
            isLoading = false
            activity_detail_movie_fab_favorite.setImageResource(R.drawable.ic_favourite_fill)
            loadData(movieEntity)
            Log.e("Detail Movie", "TRUE FAV :" + movieEntity.title)
            activity_detail_movie_fab_favorite.setOnClickListener {
                viewModel.deleteFavoriteMovie(movieEntity)
                isFavorite = false
                Log.e("Detail Movie", "FALSE FAV CLICK :" + movieEntity.title)
                setData(movieEntity, viewModel)
            }
        } else {
            activity_detail_movie_fab_favorite.setImageResource(R.drawable.ic_favourite_empty)
            viewModel.setMovieId(movieEntity.id)
            viewModel.getMovieDetail().observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> {
                            activity_detail_movie_progressBar_layout.visibility = View.VISIBLE
                            isLoading = true
                        }
                        Status.SUCCESS -> {
                            Log.e("Detail Movie", "FALSE FAV :" + movieEntity.title)
                            activity_detail_movie_fab_favorite.setOnClickListener {
                                viewModel.insertFavoriteMovie(movieEntity)
                                if (movie.data != null) {
                                    Log.e("Detail Movie", "TRUE FAV CLICK :" + movieEntity.title)
                                    isFavorite = true
                                    setData(movie.data!!, viewModel)
                                }
                            }
                            activity_detail_movie_progressBar_layout.visibility = View.GONE
                            isLoading = false
                            if (movie.data != null)
                                loadData(movie.data)
                        }
                        Status.ERROR -> {
                            activity_detail_movie_progressBar_layout.visibility = View.GONE
                            isLoading = false
                            Toast.makeText(
                                this,
                                resources.getString(R.string.error_message),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            })
        }
    }

    private fun loadData(
        movieEntity: MovieEntity?,
    ) {
        movieEntity?.let {
            Picasso.get().load(IMAGE_URL + it.posterPath).fit()
                .placeholder(R.drawable.loading_decor).error(R.drawable.ic_imageerror)
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

    override fun onBackPressed() {
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        this.menu = menu
        viewModel.checkFavorite()?.observe(this, { state ->
            Log.e("Detail Movie", "ONCREATE MENU FAV:$state")
            isFavorite = if (state != null) {
                Log.e("Detail Movie", "ONCREATE MENU TRUE FAV:$state")
                state == 1
            } else {
                Log.e("Detail Movie", "ONCREATE MENU FALSE FAV:$state")
                false
            }
            Log.e("Detail Movie", "ONCREATE MENU STATE FAV:$isFavorite")
            favoriteState(isFavorite)
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.activity_detail_action_favorite -> if (!isLoading) {
                if (isFavorite) {
                    Log.e("Detail Movie", "TRUE FAV B4 CLICK DEL :$isFavorite")
                    viewModel.deleteFavorite()
                    Log.e("Detail Movie", "TRUE FAV A4 CLICK DEL :$isFavorite")
                } else
                    Log.e("Detail Movie", "FALSE FAV B4 CLICK DEL :$isFavorite")
                viewModel.insertFavorite()
                Log.e("Detail Movie", "FALSE FAV A4 CLICK DEL :$isFavorite")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun favoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.activity_detail_action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favourite_fill)
//            activity_detail_movie_fab_favorite.setImageResource(R.drawable.ic_favourite_fill)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favourite_empty)
//            activity_detail_movie_fab_favorite.setImageResource(R.drawable.ic_favourite_empty)
        }
    }

}
