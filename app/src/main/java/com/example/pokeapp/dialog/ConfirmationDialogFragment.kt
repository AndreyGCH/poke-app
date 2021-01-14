package com.example.pokeapp.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.pokeapp.R
import com.example.pokeapp.adapters.FavAdapter
import com.example.pokeapp.viewmodels.favListViewModel
import com.example.pokeapp.viewmodels.pokeListViewModel
import java.lang.IllegalStateException

class ConfirmationDialogFragment : DialogFragment() {
    private val viewModel: pokeListViewModel by viewModels()
    val favViewmodel: favListViewModel by viewModels()
    private val favAdapter = FavAdapter()

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        //return super.onCreateDialog(savedInstanceState)
        return activity?.let {
            var builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.RemovePoke)
                .setPositiveButton(R.string.Accept, DialogInterface.OnClickListener{
                    dialog, id ->

                })
                .setNegativeButton(R.string.Cancel, DialogInterface.OnClickListener{
                    dialog, id ->

                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}