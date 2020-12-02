package com.example.jetpackprosubmission.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowApiItem(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("genres") val genres: List<GenreApiItem>,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("last_air_date") val lastAirDate: String? = null,
    @SerializedName("vote_average") val voteAverage: String? = null,
    @SerializedName("number_of_episodes") val numberOfEpisodes: String? = null,
    @SerializedName("number_of_seasons") val numberOfSeasons: String? = null
) : Parcelable