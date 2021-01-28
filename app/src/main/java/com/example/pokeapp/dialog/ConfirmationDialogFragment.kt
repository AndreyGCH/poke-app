package com.example.pokeapp.dialog

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.pokeapp.R
import com.example.pokeapp.adapters.FavAdapter
import com.example.pokeapp.adapters.PokeAdapter
import com.example.pokeapp.db.pokemon
import com.example.pokeapp.db.pokemonDao
import com.example.pokeapp.viewmodels.favListViewModel
import com.example.pokeapp.viewmodels.pokeListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_poke_list.*


class ConfirmationDialogFragment : DialogFragment() {
    private  val disposables =  CompositeDisposable()
    private val viewModel: pokeListViewModel by viewModels()

    private val favAdapter = FavAdapter()
    private val adapter = PokeAdapter()
    var frg: Fragment? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        //return super.onCreateDialog(savedInstanceState)
        return activity?.let {

            var arg = arguments
            var builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.RemovePoke)
                .setPositiveButton(R.string.Accept, DialogInterface.OnClickListener { dialog, id ->

                    if (arg != null) {
                        var array = arg.getStringArrayList("pokemons")
                        if (array != null) {
                            array.forEach{argID ->
                                val favViewmodel: favListViewModel by viewModels()
                                if (argID.toString() == arg.getString("id").toString()) {
                                    favViewmodel.deletePoke(arg.getString("id").toString())
                                    Toast.makeText(this.context, R.string.DltFav, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }



                    }
                })
                .setNegativeButton(R.string.Cancel, DialogInterface.OnClickListener { dialog, id ->

                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}