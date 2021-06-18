package com.android.football.entity


import com.google.gson.annotations.SerializedName

data class TeamAction(
    @SerializedName("action")
    val action: Action,
    @SerializedName("actionType")
    val actionType: Int,
    @SerializedName("teamType")
    val teamType: Int,
)