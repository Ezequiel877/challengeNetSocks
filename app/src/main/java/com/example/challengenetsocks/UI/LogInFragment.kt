package com.example.challengenetsocks.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.challengenetsocks.utils.Result
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.challengenetsocks.R
import com.example.challengenetsocks.databinding.FragmentLogInBinding
import com.example.challengenetsocks.domian.SingInImp
import com.example.challengenetsocks.domian.UserDataSource
import com.example.challengenetsocks.model.AuthFactory
import com.example.challengenetsocks.model.ModelUserViewModel


class LogInFragment : Fragment(R.layout.fragment_log_in) {
    private lateinit var binding:FragmentLogInBinding
    private val viewModel by viewModels<ModelUserViewModel> {
        AuthFactory(SingInImp(UserDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentLogInBinding.bind(view)

        bindingCheck()
        goingLoging()
    }

    private fun bindingCheck() {
        binding.buttonIn.setOnClickListener {
            val email = binding.InPutText.text.toString().trim()
            val password = binding.InPutTextAdress.text.toString().trim()
            reliaceEmail_Passwork(email, password)
            logIn(email, password)
        }

    }
    private fun goingLoging(){
        binding.buttonIn.setOnClickListener {
            findNavController().navigate(R.id.singInFragment)

        }
    }

    private fun reliaceEmail_Passwork(email: String, contraseña: String) {
        if (email.isEmpty()) {
            binding.InPutText.error = "ERROR email is empty"
            return
        }
        if (contraseña.isEmpty()) {
            binding.InPutText.error = "ERROR contraseña is empty"
            return

        }
    }

    private fun logIn(email: String, password: String) {
        viewModel.singIn(email, password).observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Result.Loading ->{
                    binding.progresRigt.visibility=View.VISIBLE
                    binding.buttonIn.isEnabled=false
                }
                is Result.Succes->{
                    binding.progresRigt.visibility=View.GONE

                }
                is Result.Failure->{
                    binding.progresRigt.visibility=View.GONE
                    binding.buttonIn.isEnabled=true
                }

                else -> {}
            }
        })

    }
}