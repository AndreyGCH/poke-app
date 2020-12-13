package com.example.pokeapp.network

import com.example.pokeapp.models.pokeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeService {
    @GET("pokemon")
    fun getPokeList(@Query("limit")limit:Int): Call<pokeResponse>
}