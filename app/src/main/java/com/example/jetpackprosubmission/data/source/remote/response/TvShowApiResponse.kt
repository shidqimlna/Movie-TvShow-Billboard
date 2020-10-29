package com.example.jetpackprosubmission.data.source.remote.response

import android.os.Parcelable
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowApiResponse(
    val results: List<TvShowEntity>
) : Parcelable