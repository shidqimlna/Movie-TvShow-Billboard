package com.example.jetpackprosubmission.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem(
    val id: String? = null,
    val title: String? = null,
    val poster_path: String? = null,
    val overview: String? = null,
    val runtime: String? = null,
    val release_date: String? = null,
    val vote_average: String? = null,
    val genres: ArrayList<GenreItem>? = null
) : Parcelable