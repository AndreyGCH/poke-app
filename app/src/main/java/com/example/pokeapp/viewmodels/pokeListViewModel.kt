package com.example.pokeapp.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.models.PokeDataResponse
import com.example.pokeapp.models.PokeDetails
import com.example.pokeapp.models.PokeNames
import com.example.pokeapp.models.PokeResponse
import com.example.pokeapp.network.RetrofitProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class pokeListViewModel : ViewModel(){
    private val retrofitProvider = RetrofitProvider()

    //private val pokeListResponse : MutableLiveData<List<PokeNames>> = MutableLiveData()
    private val isMakingRequest: MutableLiveData<Boolean> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()

//    fun getPokeListResponse(): LiveData<List<PokeNames>>{
//        return pokeListResponse
//    }

    fun getIsMakingRequest(): MutableLiveData<Boolean>{
        return isMakingRequest
    }

    fun getIsError(): LiveData<Boolean>{
        return isError
    }

    fun getPokeList() :Observable<List<PokeDetails>>{
        isMakingRequest.postValue(true)
        val limit = 20
        return retrofitProvider.getPokeService().getPokeList(limit)
                .map { response -> response.results }
                .doOnError { isError.postValue(true)}
                .onErrorReturn { emptyList() }
                .flatMapIterable { list -> list }
                .flatMap { item -> retrofitProvider.getPokeService().getPokeDetailList(item.name)
                    .map {detailResponse -> detailResponse}}
                .toList()
                .toObservable()
                .doOnNext { isMakingRequest.postValue(false) }

//        retrofitProvider.getPokeService().getPokeList(limit).enqueue(object : Callback<PokeResponse>{
//            override fun onResponse(call: Call<PokeResponse>, response: Response<PokeResponse>) {
//                isMakingRequest.postValue(false)
//                if (response.isSuccessful){
//                    response.body()?.let{ unwrappedResponse ->
//                        pokeListResponse.postValue(unwrappedResponse.results)
//
//                        Log.d("Lista", unwrappedResponse.results.toString())
//
//                        unwrappedResponse.results.forEachIndexed{ index, poke ->
//                            println(poke.name + ' '+ poke.url)
//                            getPokeDetailList(poke.name)
//                        }
////                        for (i in unwrappedResponse.results) {
////                            println(i.name + ' '+ i.url)
////                            getPokeDetailList(i.toString())
////                        }
//                    }
//
//                }else {
//                    isError.postValue(true)
//                }
//            }
//
//            override fun onFailure(call: Call<PokeResponse>, t: Throwable) {
//                isMakingRequest.postValue(false)
//                Log.d("Lista", "Falla")
//            }
//        })
    }

//    fun getPokeDetailList(name: String) {
//        //var ind = index + 1
//        retrofitProvider.getPokeService().getPokeDetailList(name).enqueue(object : Callback<PokeDetails> {
//            override fun onResponse(call: Call<PokeDetails>, response: Response<PokeDetails>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { unwrappedResponse ->
//                        Log.d("Lista", unwrappedResponse.toString())
//                    }
//                } else {
//                    Log.d("Error", "Error Detail")
//                }
//            }
//
//
//            override fun onFailure(call: Call<PokeDetails>, t: Throwable) {
//                Log.d("Error", "Error Error")
//            }
//
//        })
//    }
}