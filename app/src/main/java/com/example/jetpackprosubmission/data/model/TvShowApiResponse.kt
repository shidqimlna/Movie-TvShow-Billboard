package com.example.jetpackprosubmission.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowApiResponse(
    val results: ArrayList<TvShowEntity>
) : Parcelable