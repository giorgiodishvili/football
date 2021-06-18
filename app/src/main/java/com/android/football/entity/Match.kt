package com.android.football.entity


import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("matchDate")
    val matchDate: Long,
    @SerializedName("matchSummary")
    val matchSummary: MatchSummary,
    @SerializedName("matchTime")
    val matchTime: Double,
    @SerializedName("stadiumAdress")
    val stadiumAdress: String,
    @SerializedName("team1")
    val team: Team,
    @SerializedName("team2")
    val team2: Team
)