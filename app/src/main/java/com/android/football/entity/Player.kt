package com.android.football.entity


import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("playerImage")
    val playerImage: String? =null,
    @SerializedName("playerName")
    val playerName: String
)