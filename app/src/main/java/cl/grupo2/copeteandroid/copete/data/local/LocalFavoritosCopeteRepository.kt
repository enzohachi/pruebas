package cl.grupo2.copeteandroid.copete.data.local

import cl.grupo2.copeteandroid.copete.domain.Copete
import cl.grupo2.copeteandroid.copete.domain.CopeteFavoritosRepository

class LocalFavoritosCopeteRepository:CopeteFavoritosRepository {
    override suspend fun addAFavoritos(copete: Copete): Boolean {
        return true
    }
}