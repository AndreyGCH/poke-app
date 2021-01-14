package com.example.pokeapp.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.models.PokeDetails
import com.example.pokeapp.models.PokeNames
import com.example.pokeapp.models.pokemon
import com.example.pokeapp.viewholders.pokeViewHolder
import com.example.pokeapp.viewmodels.pokeListViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class PokeAdapter: RecyclerView.Adapter<pokeViewHolder>() {

    private val databaseClicksItem : PublishSubject<PokeDetails> = PublishSubject.create()
    val databaseItemClick : Observable<PokeDetails> = databaseClicksItem.hide()


    private val clickListener: PublishSubject<PokeDetails> =  PublishSubject.create()

    val onPokeClicked: Observable<PokeDetails> = clickListener.hide()

    var pokemons: List<PokeDetails> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poke_item_view_holder, parent, false)
        return pokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: pokeViewHolder, position: Int) {
        holder.bind(pokemons[position], clickListener, databaseClicksItem)
    }

    override fun getItemCount() = pokemons.size

}