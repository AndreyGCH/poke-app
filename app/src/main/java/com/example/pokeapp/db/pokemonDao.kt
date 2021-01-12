package com.example.pokeapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokeapp.models.PokeDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface pokemonDao {
    @Insert
    suspend fun  insert(pokemon: pokemon)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemons(): Flow<List<pokemon>>
}