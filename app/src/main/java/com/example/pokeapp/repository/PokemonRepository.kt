package com.example.pokeapp.repository

import com.example.pokeapp.db.pokemon
import com.example.pokeapp.db.pokemonDao
import com.example.pokeapp.models.PokeDetails
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val pokemonDao: pokemonDao) {
    suspend fun insert(pokemon:pokemon){
        pokemonDao.insert(pokemon)
    }

    val allPokemons : Flow<List<pokemon>> = pokemonDao.getAllPokemons()
}