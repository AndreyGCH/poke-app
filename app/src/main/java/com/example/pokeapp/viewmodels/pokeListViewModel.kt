package com.example.pokeapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.pokeapp.db.pokemon
import com.example.pokeapp.db.pokemonDatabase
import com.example.pokeapp.models.PokeDetails
import com.example.pokeapp.network.RetrofitProvider
import com.example.pokeapp.repository.PokemonRepository
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.launch

class pokeListViewModel (application: Application) :  AndroidViewModel(application){
    private val repository: PokemonRepository
    val database: pokemonDatabase
    init {
        database = pokemonDatabase.getDatabase(application)
        repository = PokemonRepository(database.pokemonDao())
    }

    private val retrofitProvider = RetrofitProvider()
    private val isMakingRequest: MutableLiveData<Boolean> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()



    fun insert(pokemon: pokemon){
        viewModelScope.launch {
            repository.insert(pokemon)
        }
        return
    }

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


    }

}