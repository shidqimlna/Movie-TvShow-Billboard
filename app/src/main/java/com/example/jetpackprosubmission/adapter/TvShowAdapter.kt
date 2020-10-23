package com.example.jetpackprosubmission.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.model.TvShowItem
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.example.jetpackprosubmission.view.activity.TvShowDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ListViewHolder>() {

    private val listTvShows = ArrayList<TvShowItem>()

    fun setData(items: Collection<TvShowItem>) {
        listTvShows.clear()
        listTvShows.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(tvShow: TvShowItem?) {
            tvShow?.let {
                with(itemView) {
                    Picasso.get().load(IMAGE_URL + it.poster_path).into(item_movie_iv_poster)
                    item_movie_tv_title.text = it.name
                    item_movie_tv_rating.text = it.vote_average
                    item_movie_tv_overview.text = it.overview
                    item_movie_cardView.setOnClickListener {
                        val intent = Intent(context, TvShowDetailActivity::class.java)
                        intent.putExtra("tvShow", tvShow)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): TvShowAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_tvshow, viewGroup, false)
        )

    override fun onBindViewHolder(holder: TvShowAdapter.ListViewHolder, position: Int) {
        holder.bindView(listTvShows[position])
    }

    override fun getItemCount(): Int = listTvShows.size

}