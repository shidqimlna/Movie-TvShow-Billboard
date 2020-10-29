package com.example.jetpackprosubmission.data.source.remote.response

import android.os.Parcelable
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieApiResponse(
    val results: List<MovieEntity>
) : Parcelable