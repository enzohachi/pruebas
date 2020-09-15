package cl.grupo2.copeteandroid.copete.domain

class AgregarAFavoritosUseCase (private val repository: CopeteFavoritosRepository){
    suspend fun execute(copete: Copete)= repository.addAFavoritos(copete)
}