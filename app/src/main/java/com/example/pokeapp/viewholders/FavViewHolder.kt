package com.example.pokeapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapp.db.pokemon
import kotlinx.android.synthetic.main.fav_view_holder.view.*
import kotlinx.android.synthetic.main.poke_item_view_holder.view.*

class FavViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    fun bind(pokemon: pokemon){
        itemView.txtFavPoke.text = pokemon.name
        itemView.txtFavPokeTyoe.text = pokemon.types

        Glide.with(itemView.context)
                .load(pokemon.sprites)
                .into(itemView.imgFavPoke);

    }
}