package com.example.jetpackprosubmission.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieApiResponse(
    val results: List<MovieApiItem>
) : Parcelable