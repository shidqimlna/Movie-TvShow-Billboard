package com.example.jetpackprosubmission.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowEntity(
    val id: String? = null,
    val name: String? = null,
    val poster_path: String? = null,
    val overview: String? = null,
    val first_air_date: String? = null,
    val last_air_date: String? = null,
    val vote_average: String? = null,
    val number_of_episodes: String? = null,
    val number_of_seasons: String? = null,
    val genres: ArrayList<GenreItem>? = null
) : Parcelable