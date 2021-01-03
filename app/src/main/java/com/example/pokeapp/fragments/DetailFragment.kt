package com.example.pokeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.poke_item_view_holder.view.*

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(imgPokeDetail.context)
                .load(args.pokeDetails.sprites.front_default)
                .into(imgPokeDetail);

        lblPokeName.text = args.pokeDetails.name

        txtPokeId.text =  args.pokeDetails.id
        txtPokeExp.text = args.pokeDetails.base_experience
        lblPokeStat.text = args.pokeDetails.stats[0].stat.name+ ":"
        txtPokeStat.text = args.pokeDetails.stats[0].base_stat
        txtPokeEfford.text= args.pokeDetails.stats[0].effort
        txtPokeHeight.text = args.pokeDetails.height
        txtPokeWeight.text = args.pokeDetails.weight
        txtPokeMove.text = args.pokeDetails.moves[0].move.name
        txtPokeType.text = args.pokeDetails.types[0].type.name
    }
}