package cl.grupo2.copeteandroid.autentificacion.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cl.grupo2.copeteandroid.R
import cl.grupo2.copeteandroid.databinding.FragmentLoginBinding
import cl.grupo2.copeteandroid.autentificacion.login.data.remote.FirebaseAutentificacionRepository
import cl.grupo2.copeteandroid.autentificacion.login.domain.LoginUserPassUseCase
import cl.grupo2.copeteandroid.autentificacion.login.domain.UserAuth
import cl.grupo2.copeteandroid.autentificacion.login.presentation.LoginUiState
import cl.grupo2.copeteandroid.autentificacion.login.presentation.LoginViewModel
import cl.grupo2.copeteandroid.autentificacion.login.presentation.LoginViewModelFactory
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginUsuarioPassUseCase: LoginUserPassUseCase
    private lateinit var repository: FirebaseAutentificacionRepository
    private lateinit var loginViewModelFactory: LoginViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentLoginBinding.bind(view)
        setupLiveData()
        setupListeners()
    }

    private fun setupLiveData() {
        loginViewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state ->
                handleState(state)
            }
        )
    }

    private fun handleState(state: LoginUiState?) {
        when (state) {
            is LoginUiState.Loading -> mostrarCargando()
            is LoginUiState.Invaliduser -> mostrarErrorContrasena()
            is LoginUiState.Success -> mostrarLoginCorrecto(state.result)
            is LoginUiState.Error -> mostrarError()
        }
    }

    private fun mostrarError() {
        Toast.makeText(requireContext(), "Ha ocurrido un error", Toast.LENGTH_LONG).show()
    }

    private fun mostrarLoginCorrecto(result: UserAuth?) {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_loginFragment_to_mainFragment)
    }

    private fun mostrarErrorContrasena() {
        Toast.makeText(requireContext(), "Contraseña Incorrecta", Toast.LENGTH_LONG).show()
    }

    private fun mostrarCargando() {
        Toast.makeText(requireContext(), "Cargando", Toast.LENGTH_SHORT).show()
    }

    private fun setupDependencies() {
        repository = FirebaseAutentificacionRepository(FirebaseAuth.getInstance())
        loginUsuarioPassUseCase = LoginUserPassUseCase(repository)
        loginViewModelFactory = LoginViewModelFactory(loginUsuarioPassUseCase)
        loginViewModel =
            ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }

    private fun setupListeners() {
        binding.apply {
            btnIngresar.setOnClickListener {
                val email = etEmailLogin.text.toString()
                val clave = etPasswordLogin.text.toString()
                callLoginViewModel(email, clave)
            }

            btnRegistrarseRegistro.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_registroUsuarioFragment)
            }
        }
    }

    private fun callLoginViewModel(email: String, clave: String) {
        loginViewModel.doLogin(email, clave)
    }

}