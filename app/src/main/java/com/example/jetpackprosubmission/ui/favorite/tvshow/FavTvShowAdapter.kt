package com.example.jetpackprosubmission.ui.favorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteTvShowEntity
import com.example.jetpackprosubmission.ui.tvshow.TvShowDetailActivity
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tvshow.view.*

class FavTvShowAdapter :
    PagedListAdapter<FavoriteTvShowEntity, FavTvShowAdapter.ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteTvShowEntity>() {
            override fun areItemsTheSame(
                oldItem: FavoriteTvShowEntity,
                newItem: FavoriteTvShowEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteTvShowEntity,
                newItem: FavoriteTvShowEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_tvshow, viewGroup, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null)
            holder.bindView(tvShow)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(tvShow: FavoriteTvShowEntity?) {
            tvShow?.let {
                with(itemView) {
                    Picasso.get().load(IMAGE_URL + it.posterPath).fit()
                        .placeholder(R.drawable.loading_decor).error(R.drawable.ic_error)
                        .into(item_tvshow_iv_poster)
                    item_tvshow_tv_title.text = it.name
                    item_tvshow_tv_rating.text = it.voteAverage
                    item_tvshow_tv_overview.text = it.overview
                    item_tvshow_cardView.setOnClickListener {
                        val intent = Intent(context, TvShowDetailActivity::class.java)
                        intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW_FAVORITE, tvShow)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}