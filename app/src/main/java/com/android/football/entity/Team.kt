package com.android.football.entity


import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("ballPosition")
    val ballPosition: Int,
    @SerializedName("score")
    val score: Int,
    @SerializedName("teamImage")
    val teamImage: String,
    @SerializedName("teamName")
    val teamName: String
)