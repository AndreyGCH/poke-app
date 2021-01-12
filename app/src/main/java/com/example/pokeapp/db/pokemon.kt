package com.example.pokeapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapp.models.PokeMove
import com.example.pokeapp.models.PokeSprite
import com.example.pokeapp.models.PokeStats
import com.example.pokeapp.models.PokeTypes

@Entity
data class pokemon(@PrimaryKey val id: String,
                   @ColumnInfo(name = "base_experience") val base_experience: String,
                   @ColumnInfo(name = "height")val height: String,
                   @ColumnInfo(name = "weight") val weight: String,
                   @ColumnInfo(name = "name") val name:String,
                   @ColumnInfo(name = "sprites") val sprites: String,
                   @ColumnInfo(name = "moves") val moves: String,
                   @ColumnInfo(name = "stats") val stat: String,
                   @ColumnInfo(name = "efford") val efford: String,
                   @ColumnInfo(name = "base_stat") val base_stat: String,
                   @ColumnInfo(name = "types") val types: String
)
