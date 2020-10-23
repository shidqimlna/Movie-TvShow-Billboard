package com.example.jetpackprosubmission.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreItem(
    val name: String? = null
) : Parcelable