package com.android.football.api


import com.android.football.entity.Game
import retrofit2.Response
import retrofit2.http.GET


interface FootballApi {


    @GET("48bb936e-261a-4471-a362-3bbb3b9a2a58/")
    suspend fun getMatch(): Response<Game>

}