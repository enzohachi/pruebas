package cl.grupo2.copeteandroid.domain

import cl.grupo2.copeteandroid.data.DataSource
import cl.grupo2.copeteandroid.data.model.Drink
import cl.grupo2.copeteandroid.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {
    override suspend fun getTragosList(tragoName: String): Resource<List<Drink>> {
        return dataSource.getTragoByName(tragoName)
    }


}