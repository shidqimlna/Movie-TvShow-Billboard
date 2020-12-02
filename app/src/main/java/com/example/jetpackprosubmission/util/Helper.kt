package com.example.jetpackprosubmission.util

import com.example.jetpackprosubmission.data.source.remote.response.GenreApiItem

object Helper {
    fun getGenres(responseGenre: List<GenreApiItem>): String? {
        val listGenres = ArrayList<String?>()
        for (genre in responseGenre) {
            listGenres.add(genre.name)
        }
        return genresFormatting(listGenres)
    }

    fun runtimeFormatting(time: Int): String {
        val hours = time / 60
        val minutes = time % 60
        return when {
            hours > 0 -> "${hours}h ${minutes}m"
            else -> "${minutes}m"
        }
    }

    private fun genresFormatting(listGenres: List<String?>): String? {
        var genres: String? = ""
        for (i in listGenres.indices) {
            genres = if (i == 0) listGenres[i] else "$genres, ${listGenres[i]}"
        }
        return genres
    }
}