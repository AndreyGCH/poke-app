package com.example.pokeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.db.pokemon
import com.example.pokeapp.viewholders.FavViewHolder

class FavAdapter(): RecyclerView.Adapter<FavViewHolder>() {
    var pokemons : List<pokemon> = emptyList()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_view_holder, parent, false)
        return FavViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount() = pokemons.size
}