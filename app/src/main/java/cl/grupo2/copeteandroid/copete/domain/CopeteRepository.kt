package cl.grupo2.copeteandroid.copete.domain

import cl.grupo2.copeteandroid.copete.presentation.Resource

interface CopeteRepository {
    suspend fun getCopeteList(copeteName: String) : Resource<List<Copete>>
}

