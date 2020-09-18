package cl.grupo2.copeteandroid.autentificacion.registro.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.grupo2.copeteandroid.R
import cl.grupo2.copeteandroid.autentificacion.utils.extensions.*
import cl.grupo2.copeteandroid.databinding.FragmentRegistroUsuarioBinding
import cl.grupo2.copeteandroid.autentificacion.registro.data.remote.FirebaseRegistroRepository
import cl.grupo2.copeteandroid.autentificacion.registro.domain.RegistraUsuarioUseCase
import cl.grupo2.copeteandroid.autentificacion.registro.domain.RegistroUsuario
import cl.grupo2.copeteandroid.autentificacion.registro.presentation.RegistroUiState
import cl.grupo2.copeteandroid.autentificacion.registro.presentation.RegistroUsuarioViewModel
import cl.grupo2.copeteandroid.autentificacion.registro.presentation.RegistroViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegistroUsuarioFragment : Fragment (R.layout.fragment_registro_usuario){

    lateinit var binding : FragmentRegistroUsuarioBinding
    lateinit var viewModel: RegistroUsuarioViewModel
    lateinit var viewModelFactory: RegistroViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentRegistroUsuarioBinding.bind(view)
        setupLiveData()
        setuplistener()
    }

    private fun setupDependencies() {
        viewModelFactory =
            RegistroViewModelFactory(
                RegistraUsuarioUseCase(
                    FirebaseRegistroRepository(
                        FirebaseAuth.getInstance(),
                        FirebaseDatabase.getInstance()
                    )
                )
            )
        viewModel =
            ViewModelProvider(this,viewModelFactory).get(RegistroUsuarioViewModel::class.java)
    }

    private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner, Observer { handleState(it) })

    }

    private fun setuplistener() {
        binding.apply {
            btnRegistrarseRegistro.setOnClickListener{
                if (isAllValidInputs()){
                    viewModel.registrarUsuario(obtenerDatosUsuario())
                }
            }
            btnVolverRegistro.setOnClickListener{
                activity?.onBackPressed()
            }
        }
    }

    private fun obtenerDatosUsuario(): RegistroUsuario {
        binding.apply {
            return RegistroUsuario(
                etNombreRegistro.text.toString(),
                etRutRegistro.text.toString(),
                etEmailRegistro.text.toString(),
                etPasswordRegistro.text.toString()
            )
        }

    }

    private fun isAllValidInputs(): Boolean {
        binding.apply {
            return etNombreRegistro.isValidNameInput("Ingrese nombre") ||
                    etEmailRegistro.isValidEmailInput("Ingrese correo valido")||
                    etRutRegistro.isValidRutInput("Ingrese un Rut Valido")||
                    etPasswordRegistro.isValidPassInput("Ingrese contraseña con 6 o mas caracteres")||
                    etPasswordConfirmRegistro.isValidPassConfirm(etPasswordRegistro.text.toString(),"La contraseña debe coincidir con la anterior")
        }
    }


    private fun handleState(estado: RegistroUiState?) {
        when(estado){
            is RegistroUiState.LoadingRegistroUiState -> showLoading()
            is RegistroUiState.InvalidEmailRegistroUiState -> showInvalidEmail()
            is RegistroUiState.SuccessRegistroUiState -> showSuccessRegistro()
            is RegistroUiState.ErrorRegistroUiState -> showErrorRegistro()
        }
    }

    private fun showErrorRegistro() {
        alert("Error en el registro")
    }

    private fun showSuccessRegistro() {
        alert("Registro Exitoso")
    }

    private fun showInvalidEmail() {
        alert("Error en correo electronico")
    }

    private fun showLoading() {
        alert("Espere el a completar registro")
    }

}