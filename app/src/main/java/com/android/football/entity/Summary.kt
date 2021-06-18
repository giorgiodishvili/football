package com.android.football.entity


import com.google.gson.annotations.SerializedName

data class Summary(
    @SerializedName("actionTime")
    val actionTime: String,
    @SerializedName("team1Action")
    val team1Action: List<TeamAction?>?,
    @SerializedName("team2Action")
    val team2Action: List<TeamAction?>?
)