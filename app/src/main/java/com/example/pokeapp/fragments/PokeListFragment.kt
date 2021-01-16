package com.example.pokeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.pokeapp.R
import com.example.pokeapp.adapters.FavAdapter
import com.example.pokeapp.adapters.PokeAdapter
import com.example.pokeapp.dialog.ConfirmationDialogFragment
import com.example.pokeapp.models.pokemon
import com.example.pokeapp.viewmodels.favListViewModel
import com.example.pokeapp.viewmodels.pokeListViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_poke_list.*
import java.util.concurrent.TimeUnit

class PokeListFragment : Fragment() {
    var dialog = ConfirmationDialogFragment()
    var deletedPokemon = ""
    private var deleteCount = 0
    private val adapter = PokeAdapter()
    private val favAdapter = FavAdapter()
    private  val disposables =  CompositeDisposable()
    private val viewModel: pokeListViewModel by viewModels()
    val favViewmodel: favListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.getPokeList()
        favViewmodel.getAll().observe(viewLifecycleOwner){pokemons ->
            adapter.favPokemons =  pokemons

        }

        return inflater.inflate(R.layout.fragment_poke_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposables.clear()

        pokeRecyclerView.adapter = adapter
        pokeRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        disposables.add(adapter.onPokeClicked
                .throttleFirst(400, TimeUnit.MILLISECONDS)
                .subscribe{pokemon ->
                    val action = PokeListFragmentDirections.actionPokeListFragment2ToDetailFragment3(pokemon)
                    findNavController().navigate(action)
                }
        )

        viewModel.getPokeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {pokeList->
                adapter.pokemons = pokeList
                pokeRecyclerView.visibility = View.VISIBLE
            }

        viewModel.getIsMakingRequest().observe(viewLifecycleOwner){isMakingRequest ->
            progressBar.visibility = if(isMakingRequest) View.VISIBLE else View.GONE

        }
        viewModel.getIsError().observe(viewLifecycleOwner){isError ->
            Snackbar.make(parent, R.string.error_text, Snackbar.LENGTH_LONG).show()
        }

        var flag = false

        disposables.add(adapter.onPokeDeleteClicked.observeOn(AndroidSchedulers.mainThread())
                .subscribe{ pokemon->

                    favViewmodel.getAll().observe(viewLifecycleOwner){pokemons ->
                        if (deletedPokemon != pokemon.id) {
                            var flagfav = false
                            val bundle = Bundle()
                            bundle.putString("id", pokemon.id)
                            bundle.putString("base_experience", pokemon.base_experience)
                            bundle.putString("height", pokemon.height)
                            bundle.putString("weight", pokemon.weight)
                            bundle.putString("name", pokemon.name)
                            bundle.putString("sprites", pokemon.sprites.front_default)
                            bundle.putString("moves", pokemon.moves[0].move.name)
                            bundle.putString("stat", pokemon.stats[0].stat.name)
                            bundle.putString("effort", pokemon.stats[0].effort)
                            bundle.putString("base_stat", pokemon.stats[0].base_stat)
                            bundle.putString("type", pokemon.types[0].type.name)
                            val pokeList: ArrayList<String> = ArrayList()
                            pokemons.map { poke ->
                                pokeList.add(poke.id)
                                if (pokemon.id == poke.id) {
                                    flagfav = true
                                }
                            }

                            bundle.putStringArrayList("pokemons", pokeList)
                            dialog.arguments = bundle
                            if (flagfav) {
                                dialog.show(this.parentFragmentManager, tag)
                                deleteCount++
                                deletedPokemon = pokemon.id
                            }
                        } else {
                            deleteCount = 0
                        }

                    }
                })

        disposables.add(adapter.databaseItemClick.observeOn(AndroidSchedulers.mainThread())
                .subscribe{pokemon->
                    var count = 0
                    var flagfav = false
                    favViewmodel.getAll().observe(viewLifecycleOwner){pokemons ->
                        if (count === 0) {
                            pokemons.map { poke ->
                                if (pokemon.id == poke.id) {
                                    flagfav = true
                                }
                            }
                            if(!flagfav){
                                viewModel.insert(com.example.pokeapp.db.pokemon(pokemon.id, pokemon.base_experience,
                                    pokemon.height, pokemon.weight, pokemon.name, pokemon.sprites.front_default,
                                    pokemon.moves[0].move.name, pokemon.stats[0].stat.name, pokemon.stats[0].effort,
                                    pokemon.stats[0].base_stat, pokemon.types[0].type.name))
                                Toast.makeText(this.context, R.string.AddFav, Toast.LENGTH_SHORT).show()
                                count++
                            }

                        }

                    }
        })

    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()

    }


}