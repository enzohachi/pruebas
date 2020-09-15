package cl.grupo2.copeteandroid.copete.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.grupo2.copeteandroid.copete.domain.AgregarAFavoritosUseCase
import cl.grupo2.copeteandroid.copete.domain.CopeteRepository

class VMFactory(
    private val copeteRepository: CopeteRepository,
    private val agregarAFavoritosUseCase: AgregarAFavoritosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            CopeteRepository::class.java,
            AgregarAFavoritosUseCase::class.java
        ).newInstance(copeteRepository, agregarAFavoritosUseCase)
    }
}