package com.example.jetpackprosubmission.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackprosubmission.R
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.util.ConstantValue.IMAGE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private val listMovies = ArrayList<MovieEntity>()

    fun setData(entities: Collection<MovieEntity>) {
        listMovies.clear()
        listMovies.addAll(entities)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(movie: MovieEntity?) {
            movie?.let {
                with(itemView) {
                    Picasso.get().load(IMAGE_URL + it.poster_path).fit()
                        .placeholder(R.drawable.loading_decor).error(R.drawable.ic_imageerror)
                        .into(item_movie_iv_poster)
                    item_movie_tv_title.text = it.title
                    item_movie_tv_rating.text = it.vote_average
                    item_movie_tv_overview.text = it.overview
                    item_movie_cardView.setOnClickListener {
                        val intent = Intent(context, MovieDetailActivity::class.java)
                        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie.id)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}