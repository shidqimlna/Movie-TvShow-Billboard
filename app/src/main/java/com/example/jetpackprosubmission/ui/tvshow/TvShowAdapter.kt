package com.example.jetpackprosubmission.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.data.TvShowEntity
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tvshow.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ListViewHolder>() {

    private val listTvShows = ArrayList<TvShowEntity>()

    fun setData(entities: Collection<TvShowEntity>) {
        listTvShows.clear()
        listTvShows.addAll(entities)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_tvshow, viewGroup, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(listTvShows[position])
    }

    override fun getItemCount(): Int = listTvShows.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(tvShow: TvShowEntity?) {
            tvShow?.let {
                with(itemView) {
                    Picasso.get().load(IMAGE_URL + it.poster_path).fit()
                        .placeholder(R.drawable.loading_decor).error(R.drawable.ic_imageerror)
                        .into(item_tvshow_iv_poster)
                    item_tvshow_tv_title.text = it.name
                    item_tvshow_tv_rating.text = it.vote_average
                    item_tvshow_tv_overview.text = it.overview
                    item_tvshow_cardView.setOnClickListener {
                        val intent = Intent(context, TvShowDetailActivity::class.java)
                        intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW, tvShow.id)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}