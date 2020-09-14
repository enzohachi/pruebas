package cl.grupo2.copeteandroid.domain

import cl.grupo2.copeteandroid.data.model.Drink
import cl.grupo2.copeteandroid.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("search.php")
    suspend fun getTragoByName (@Query(value = "s") tragoName : String): DrinkList
}