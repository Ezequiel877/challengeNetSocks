package com.example.challengenetsocks.UI

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.challengenetsocks.utils.Result
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.challengenetsocks.R
import com.example.challengenetsocks.databinding.FragmentSingInBinding
import com.example.challengenetsocks.domian.SingInImp
import com.example.challengenetsocks.domian.UserDataSource
import com.example.challengenetsocks.model.AuthFactory
import com.example.challengenetsocks.model.ModelUserViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SingInFragment : Fragment(R.layout.fragment_sing_in) {
    private lateinit var binding: FragmentSingInBinding
    private val viewModel by viewModels<ModelUserViewModel> {
        AuthFactory(SingInImp(UserDataSource()))
    }

    private var fechaNacimiento: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingInBinding.bind(view)
        getSeting()
        binding.txtFechaNacimiento.setOnClickListener { view -> mostrarDatePickerDialog(view) }

    }

    private fun mostrarDatePickerDialog(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, mostrarDatePickerDialog, monthOfYear, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            fechaNacimiento = dateFormat.format(selectedDate.time)
            binding.txtFechaNacimiento.text = fechaNacimiento
        }, year, month, day)

        datePickerDialog.show()
    }


    private fun getSeting() {
        binding.buttonIn.setOnClickListener {
            val name = binding.inputName.text.toString().trim()
            val email = binding.inputLastName.text.toString().trim()
            val passwork = binding.inputPassword.text.toString().trim()
            createUser(email, passwork, name)
        }
    }

    private fun createUser(email: String, passwork: String, name: String) {
        viewModel.singIn(email, passwork).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progresRigt.visibility = View.VISIBLE
                    binding.buttonIn.isEnabled = false
                }
                is Result.Succes -> {
                    binding.progresRigt.visibility = View.GONE


                }
                is Result.Failure -> {
                    binding.progresRigt.visibility = View.GONE
                    binding.buttonIn.isEnabled = false
                }
            }
        })
    }
}