package com.android.football.entity


import com.google.gson.annotations.SerializedName

data class Action(
    @SerializedName("goalType")
    val goalType: Int? = null,
    @SerializedName("player")
    val player: Player? = null,
    @SerializedName("player1")
    val player1: Player? = null,
    @SerializedName("player2")
    val player2: Player? = null
)