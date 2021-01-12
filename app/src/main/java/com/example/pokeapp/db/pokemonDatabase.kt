package com.example.pokeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(pokemon::class), version = 1, exportSchema = false)
public abstract class pokemonDatabase : RoomDatabase(){
    abstract fun pokemonDao() : pokemonDao

    companion object{
        @Volatile
        private var INSTANCE: pokemonDatabase? = null

        fun getDatabase(context: Context) : pokemonDatabase{
            return INSTANCE ?: synchronized(this){
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    pokemonDatabase::class.java,
                    "pokemon_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}