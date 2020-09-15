package cl.grupo2.copeteandroid.copete.domain

interface CopeteFavoritosRepository {
    suspend fun addAFavoritos (copete: Copete): Boolean
}