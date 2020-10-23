package com.example.jetpackprosubmission.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowApiResponse(
    val results: ArrayList<TvShowItem>
) : Parcelable