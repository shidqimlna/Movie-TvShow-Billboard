package com.example.jetpackprosubmission.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieApiResponse(
    val results: ArrayList<MovieEntity>
) : Parcelable