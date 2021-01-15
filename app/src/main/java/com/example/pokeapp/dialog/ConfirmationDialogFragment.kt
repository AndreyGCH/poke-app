package com.example.pokeapp.dialog

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.pokeapp.R
import com.example.pokeapp.adapters.FavAdapter
import com.example.pokeapp.adapters.PokeAdapter
import com.example.pokeapp.viewmodels.favListViewModel
import com.example.pokeapp.viewmodels.pokeListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable


class ConfirmationDialogFragment : DialogFragment() {
    private  val disposables =  CompositeDisposable()
    private val viewModel: pokeListViewModel by viewModels()
    val favViewmodel: favListViewModel by viewModels()
    private val favAdapter = FavAdapter()
    private val adapter = PokeAdapter()


    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        //return super.onCreateDialog(savedInstanceState)
        return activity?.let {
            disposables.clear()
            var arg = arguments
            var builder = AlertDialog.Builder(it)
            val mainHandler = Handler(Looper.getMainLooper())
            builder.setMessage(R.string.RemovePoke)
                .setPositiveButton(R.string.Accept, DialogInterface.OnClickListener { dialog, id ->

                    if (arg != null) {
                        var foo = arg.getString("id")
                        var goo = arg.getBundle("id")

//                        favViewmodel.removePoke(pokemon(arg.getString("id").toString(),arg.getString("base_experience").toString()
//                                ,arg.getString("height").toString(),arg.getString("weight").toString(),arg.getString("name").toString(),
//                                arg.getString("sprites").toString(),arg.getString("moves").toString(),arg.getString("stat").toString(),
//                                arg.getString("effort").toString(), arg.getString("base_stat").toString(),arg.getString("type").toString()))

                        var array = arg.getStringArrayList("pokemons")



                        if (array != null) {
                            array.forEach{argID ->
                                if (argID.toString() == arg.getString("id").toString()) {
                                    favViewmodel.deletePoke(arg.getString("id").toString())
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
        disposables.clear()
        super.onDestroy()

    }

}