package com.example.pokeapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.models.pokemon
import kotlinx.android.synthetic.main.poke_item_view_holder.view.*

class pokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(pokemon: pokemon){
        itemView.pokeNameTextView.text = pokemon.name
        itemView.pokeDescTextView.text = pokemon.description
    }
}