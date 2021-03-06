package com.example.pokeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.adapters.FavAdapter
import com.example.pokeapp.adapters.PokeAdapter
import com.example.pokeapp.viewmodels.favListViewModel
import kotlinx.android.synthetic.main.fragment_fav_poke_list.*

class FavPokeListFragment : Fragment() {

    private val adapter = FavAdapter()
    val viewmodel: favListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_poke_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favRecyclerView.adapter = adapter

        viewmodel.getAll().observe(viewLifecycleOwner){pokemon ->
            txtQuantity.text = "Cantidad ${pokemon.size}"
            adapter.pokemons = pokemon
        }
    }


}