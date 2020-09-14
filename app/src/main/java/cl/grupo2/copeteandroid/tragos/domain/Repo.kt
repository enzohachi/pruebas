package cl.grupo2.copeteandroid.tragos.domain

import cl.grupo2.copeteandroid.tragos.data.model.Drink
import cl.grupo2.copeteandroid.tragos.vo.Resource

interface Repo {
    suspend fun getTragosList(tragoName: String) : Resource<List<Drink>>
}