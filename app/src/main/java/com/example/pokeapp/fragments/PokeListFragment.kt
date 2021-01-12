package com.example.pokeapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.pokeapp.R
import com.example.pokeapp.adapters.PokeAdapter
import com.example.pokeapp.models.pokemon
import com.example.pokeapp.viewmodels.favListViewModel
import com.example.pokeapp.viewmodels.pokeListViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.internal.schedulers.ScheduledRunnable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_poke_list.*
import java.util.concurrent.TimeUnit

class PokeListFragment : Fragment() {
    //private val args: PokeListFragmentArgs by navArgs()
    private val adapter = PokeAdapter()
    private  val disposables =  CompositeDisposable()
    private val viewModel: pokeListViewModel by viewModels()
    private val viewModel2: favListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.getPokeList()
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

        disposables.add(adapter.databaseItemClick.subscribe{pokemon->
            viewModel.insert(com.example.pokeapp.db.pokemon(pokemon.id,pokemon.base_experience,pokemon.height
                    ,pokemon.weight,pokemon.name,pokemon.sprites.front_default,pokemon.moves[0].move.name,pokemon.stats[0].stat.name
                    ,pokemon.stats[0].effort,pokemon.stats[0].base_stat,pokemon.types[0].type.name))
        })

    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()

    }

    private fun getDummyPokeList(): List<pokemon>{
        return listOf(
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", true),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", true),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", true),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),
            pokemon("Pikachu", "https://i.pinimg.com/originals/f3/e1/b8/f3e1b8019f160f88531d8af792716b4f.png", "Electric", false),


        )
    }



}