package cl.grupo2.copeteandroid.autentificacion.registro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.grupo2.copeteandroid.autentificacion.registro.domain.RegistraUsuarioUseCase

class RegistroViewModelFactory(
    private val registarUsuarioUseCase: RegistraUsuarioUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RegistraUsuarioUseCase::class.java)
            .newInstance(registarUsuarioUseCase)
    }

}