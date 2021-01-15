package com.example.pokeapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.db.pokemon
import com.example.pokeapp.db.pokemonDatabase
import com.example.pokeapp.repository.PokemonRepository
import kotlinx.coroutines.launch

class favListViewModel(application: Application): AndroidViewModel(application) {
    private val repository: PokemonRepository
    val database: pokemonDatabase
    init {
        database = pokemonDatabase.getDatabase(application)
        repository = PokemonRepository(database.pokemonDao())
    }

    fun getAll(): LiveData<List<pokemon>> = repository.allPokemons.asLiveData()

    fun removePoke(pokemon: pokemon){
        viewModelScope.launch {
            repository.removePoke(pokemon)
        }
    }

    fun deletePoke(pokeId: String){
        viewModelScope.launch {
            repository.deletePoke(pokeId)
        }
    }
}