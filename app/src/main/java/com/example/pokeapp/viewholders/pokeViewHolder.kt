package com.example.pokeapp.viewholders


import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.models.PokeDetails
import com.example.pokeapp.models.PokeNames
import com.example.pokeapp.models.pokemon
import androidx.fragment.app.viewModels
import com.example.pokeapp.adapters.PokeAdapter
import com.example.pokeapp.viewmodels.pokeListViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.poke_item_view_holder.view.*


class pokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
   // private val viewModel: pokeListViewModel by viewModels()
    private  val disposables =  CompositeDisposable()
    private val adapter = PokeAdapter()

    fun bind(pokemon: PokeDetails, listener: Observer<PokeDetails>, databaseListener:Observer<PokeDetails>){
        itemView.pokeNameTextView.text = pokemon.name
        itemView.pokeDescTextView.text = pokemon.types[0].type.name

        Glide.with(itemView.context)
            .load(pokemon.sprites.front_default)
            .into(itemView.pokeImageView);

        //val isFavorite = if (pokemon.isFavorite) R.drawable.ic_star else R.drawable.ic_star_border
        var favImage = R.drawable.ic_star_border

        //val isFavorite = if (pokemon.isFavorite) R.drawable.ic_star else R.drawable.ic_star_border
        Glide.with(itemView.context)
            .load(favImage)
            .into(itemView.favImageView)

        itemView.setOnClickListener{
            listener.onNext(pokemon)
        }

        itemView.favImageView.setOnClickListener{
            databaseListener.onNext(pokemon)
            favImage = if(favImage == R.drawable.ic_star) R.drawable.ic_star_border else R.drawable.ic_star
            Glide.with(itemView.context)
                    .load(favImage)
                    .into(itemView.favImageView)
            if(favImage == R.drawable.ic_star){
                Toast.makeText(itemView.context, R.string.AddFav, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(itemView.context, R.string.DltFav, Toast.LENGTH_SHORT).show()
            }
        }
//        disposables.add( adapter.databaseItemClick.subscribe(
            //Toast.makeText(, "PRUEBA CLICK REACTIVO", Toast.LENGTH_SHORT).show()
              //  Log.i("OBJETO PRUEBA", "")
//            viewModel.insert(com.example.pokeapp.db.pokemon("","","",
//                "","","","","","","",""))
    }
}