package com.android.football.entity


import com.google.gson.annotations.SerializedName

data class MatchSummary(
    @SerializedName("summaries")
    val summaries: List<Summary>
)