package com.example.pokeapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.pokeapp.R
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.checked
import com.jakewharton.rxbinding4.widget.checkedChanges
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit
import kotlin.math.log


class LoginFragment : Fragment() {

    private val disposable = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposable.add(
            txtLoginTrainer.textChanges()
                    .skipInitialValue()
                    .debounce(400, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        val isValid = !TextUtils.isEmpty(txtLoginTrainer.text.toString())
                        loginButton.isEnabled = isValid
                        loginLinearLayout.error = if(TextUtils.isEmpty(txtLoginTrainer.text.toString())) "Campo requerido" else null
                    }
        )

        disposable.add(
                loginButton.clicks()
                        .throttleFirst(1000, TimeUnit.MILLISECONDS)
                        .subscribe{
                            var sex = if(radioButtonMale.isChecked) "H" else "M"
                            Log.i("radioButton", sexRadioGroup.checked().toString())

                            val action = LoginFragmentDirections.actionLoginFragmentToPokeListFragment(txtLoginTrainer.text.toString(), sex)
                            findNavController().navigate(action)
                        }
        )
    }

}