package com.example.pokeapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.models.pokemon
import kotlinx.android.synthetic.main.poke_item_view_holder.view.*

class pokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(pokemon: pokemon){
        itemView.pokeNameTextView.text = pokemon.name
        itemView.pokeDescTextView.text = pokemon.description

        Glide.with(itemView.context)
            .load(pokemon.image)
            .into(itemView.pokeImageView);

        val isFavorite = if (pokemon.isFavorite) R.drawable.ic_star else R.drawable.ic_star_border

        Glide.with(itemView.context)
            .load(isFavorite)
            .into(itemView.favImageView)
    }
}