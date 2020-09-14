package cl.grupo2.copeteandroid.domain

import cl.grupo2.copeteandroid.data.model.Drink
import cl.grupo2.copeteandroid.vo.Resource

interface Repo {
    suspend fun getTragosList(tragoName: String) : Resource<List<Drink>>
}