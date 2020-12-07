package com.example.jetpackprosubmission.ui.tvshow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteTvShowEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.di.Injection
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.example.jetpackprosubmission.vo.Status
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tvshow.*
import kotlinx.android.synthetic.main.content_tvshow_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TVSHOW_FAVORITE = "extra_tvshow_favorite"
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    private lateinit var viewModel: TvShowViewModel
    private var isFavorite = false
    lateinit var tvShowFavorite: TvShowEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)
        setSupportActionBar(activity_detail_tvshow_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        )[TvShowViewModel::class.java]

        activity_detail_tvshow_progressBar_layout.visibility = View.VISIBLE

        val extras = intent.extras
        if (extras != null) {
            val extraTvShow = extras.getParcelable<TvShowEntity>(EXTRA_TVSHOW)
            val extraTvShowFavorite =
                extras.getParcelable<FavoriteTvShowEntity>(EXTRA_TVSHOW_FAVORITE)

            if (extraTvShowFavorite != null) {
                tvShowFavorite = convertFavorite(extraTvShowFavorite)
            } else if (extraTvShow != null) tvShowFavorite = extraTvShow

            viewModel.setTvShowId(tvShowFavorite.id)
            viewModel.checkFavoriteTvShow()?.observe(this, { state ->
                isFavorite = state != null
                setData(viewModel)
            })
        }
    }

    private fun setData(viewModel: TvShowViewModel) {
        if (isFavorite) {
            activity_detail_tvshow_progressBar_layout.visibility = View.GONE
            activity_detail_tvshow_fab_favorite.setImageResource(R.drawable.ic_favorite_fill)
            activity_detail_tvshow_fab_favorite.setOnClickListener {
                viewModel.deleteFavoriteTvShow(tvShowFavorite)
            }
            loadData(tvShowFavorite)
        } else {
            activity_detail_tvshow_fab_favorite.setImageResource(R.drawable.ic_favorite_empty)
            viewModel.setTvShowId(tvShowFavorite.id)
            viewModel.getTvShowDetail().observe(this, { tvShow ->
                if (tvShow != null) {
                    if (tvShow.status == Status.SUCCESS) {
                        activity_detail_tvshow_progressBar_layout.visibility = View.GONE
                        if (tvShow.data != null) {
                            tvShowFavorite = tvShow.data!!
                            activity_detail_tvshow_fab_favorite.setOnClickListener {
                                viewModel.insertFavoriteTvShow(tvShowFavorite)
                            }
                            loadData(tvShowFavorite)
                        }
                    } else if (tvShow.status == Status.ERROR) {
                        activity_detail_tvshow_progressBar_layout.visibility = View.GONE
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

    private fun loadData(tvShow: TvShowEntity?) {
        tvShow?.let {
            Picasso.get().load(IMAGE_URL + it.posterPath).fit()
                .placeholder(R.drawable.loading_decor).error(R.drawable.ic_error)
                .into(activity_detail_tvshow_iv_poster)
            activity_detail_tvshow_tv_title.text = it.name
            activity_detail_tvshow_tv_rating.text = it.voteAverage
            activity_detail_tvshow_tv_seasons.text = it.numberOfSeasons
            activity_detail_tvshow_tv_episodes.text = it.numberOfEpisodes
            activity_detail_tvshow_tv_overview.text = it.overview
            activity_detail_tvshow_tv_firstairdate.text = it.firstAirDate
            activity_detail_tvshow_tv_lastairdate.text = it.lastAirDate
            activity_detail_tvshow_tv_genre.text = it.genres
            activity_detail_tvshow_ib_share.setOnClickListener { onShareClick(tvShow) }
        }
    }

    private fun onShareClick(tvShow: TvShowEntity?) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share")
            .setText(getString(R.string.share_text, tvShow?.name))
            .startChooser()
    }

    private fun convertFavorite(favoriteTvShowEntity: FavoriteTvShowEntity): TvShowEntity {
        return TvShowEntity(
            id = favoriteTvShowEntity.id,
            name = favoriteTvShowEntity.name,
            posterPath = favoriteTvShowEntity.posterPath,
            overview = favoriteTvShowEntity.overview,
            firstAirDate = favoriteTvShowEntity.firstAirDate,
            lastAirDate = favoriteTvShowEntity.lastAirDate,
            voteAverage = favoriteTvShowEntity.voteAverage,
            numberOfEpisodes = favoriteTvShowEntity.numberOfEpisodes,
            numberOfSeasons = favoriteTvShowEntity.numberOfSeasons,
            genres = favoriteTvShowEntity.genres
        )
    }
}
