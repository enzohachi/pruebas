package cl.grupo2.copeteandroid.copete.data.network

import cl.grupo2.copeteandroid.copete.domain.CopeteList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("search.php")
    suspend fun getCopeteByName (@Query(value = "s") copeteName : String): CopeteList
}