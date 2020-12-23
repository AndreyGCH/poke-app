package com.example.pokeapp.network

import com.example.pokeapp.models.PokeDataResponse
import com.example.pokeapp.models.PokeDetails
import com.example.pokeapp.models.PokeResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {
    @GET("pokemon")
    fun getPokeList(@Query("limit")limit:Int): Observable<PokeResponse>
    //fun getPokeList(): Call<PokeResponse>

    @GET( "pokemon/{name}")
    fun getPokeDetailList(@Path("name") name:String):Observable<PokeDetails>
}