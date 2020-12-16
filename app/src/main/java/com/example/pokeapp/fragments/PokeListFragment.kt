package com.example.pokeapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.pokeapp.R
import com.example.pokeapp.adapters.PokeAdapter
import com.example.pokeapp.models.pokemon
import com.example.pokeapp.viewmodels.pokeListViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_poke_list.*
import java.util.concurrent.TimeUnit

class PokeListFragment : Fragment() {
    private val args: PokeListFragmentArgs by navArgs()
    private val adapter = PokeAdapter()
    private  val disposables =  CompositeDisposable()
    private val viewModel: pokeListViewModel by viewModels()

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

        lblTrainerName.text = args.trainerName;
        pokeRecyclerView.adapter = adapter
        pokeRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        //adapter.pokemons = getDummyPokeList()

//        disposables.add(adapter.onPokeClicked
//                .subscribe{pokemon ->
//                    val action = PokeListFragmentDirections.actionPokeListFragmentToDetailFragment(pokemon)
//                    findNavController().navigate(action)
//                }
//        )

        viewModel.getPokeListResponse().observe(viewLifecycleOwner){pokeList ->
            Log.d("Imprimir", pokeList.toString())
            adapter.pokemons = pokeList
            pokeRecyclerView.visibility = View.VISIBLE
        }
        viewModel.getIsMakingRequest().observe(viewLifecycleOwner){isMakingRequest ->
            progressBar.visibility = if(isMakingRequest) View.VISIBLE else View.GONE

        }
        viewModel.getIsError().observe(viewLifecycleOwner){isError ->
            Snackbar.make(parent, R.string.error_text, Snackbar.LENGTH_LONG).show()
        }
       // viewModel.getPokeList()
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