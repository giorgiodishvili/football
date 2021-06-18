package com.android.football.entity


import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("match")
    val match: Match,
    @SerializedName("resultCode")
    val resultCode: Int
)