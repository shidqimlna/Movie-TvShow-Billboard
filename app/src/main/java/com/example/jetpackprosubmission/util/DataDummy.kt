package com.example.jetpackprosubmission.util

import com.example.jetpackprosubmission.data.model.MovieEntity
import com.example.jetpackprosubmission.data.model.TvShowEntity
import java.util.*

object DataDummy {
    fun generateDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "100",
                "2067",
                "/8kSerJrhrJWKLk1LViesGcnrUPE.jpg",
                "A card shark and his unwillingly-enlisted friends need to make a lot of cash quick after losing a sketchy poker match. To do this they decide to pull a heist on a small-time gang who happen to be operating out of the flat next door.",
                "105",
                "1998-03-05",
                "8.2"
            )
        )

        return movies
    }

    fun generateDummyTvShows(): ArrayList<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                "100",
                "I Am Not an Animal",
                "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
                "2004-05-10",
                "2004-06-14",
                "9.4",
                "6",
                "1"
            )
        )

        return tvShow
    }
}