package com.example.pokeapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pokeapp.models.pokeResponse
import com.example.pokeapp.network.RetrofitProvider
import retrofit2.Call
import retrofit2.Response

class pokeListViewModel : ViewModel(){
    private val retrofitProvider = RetrofitProvider()

    fun getPokeList(){
        val limit = 1118
        Log.d("Lista", "getPoke")
        retrofitProvider.getPokeService().getPokeList(limit).enqueue(object : retrofit2.Callback<pokeResponse>{
            override fun onResponse(call: Call<pokeResponse>, response: Response<pokeResponse>) {
                Log.d("Lista", "Entry")
                if (response.isSuccessful){
                    response.body()?.let{ unwrappedResponse ->
                        Log.d("Lista", unwrappedResponse.toString())
                    }

                }else {
                    Log.d("Lista", "Error")
                }
            }

            override fun onFailure(call: Call<pokeResponse>, t: Throwable) {
                Log.d("Lista", "Falla")
            }
        })
    }
}