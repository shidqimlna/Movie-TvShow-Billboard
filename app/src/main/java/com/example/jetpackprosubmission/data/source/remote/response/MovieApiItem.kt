package com.example.jetpackprosubmission.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieApiItem(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("genres") val genres: List<GenreApiItem>,
    @SerializedName("runtime") val runtime: Int? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("vote_average") val voteAverage: String? = null,
) : Parcelable
