package com.example.pokeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.models.pokemon
import com.example.pokeapp.viewholders.pokeViewHolder

class PokeAdapter: RecyclerView.Adapter<pokeViewHolder>() {

    var pokemons: List<pokemon> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poke_item_view_holder, parent, false)
        return pokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: pokeViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount() = pokemons.size

}