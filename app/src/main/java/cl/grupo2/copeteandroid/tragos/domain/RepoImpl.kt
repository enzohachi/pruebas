package cl.grupo2.copeteandroid.tragos.domain

import cl.grupo2.copeteandroid.tragos.data.DataSource
import cl.grupo2.copeteandroid.tragos.data.model.Drink
import cl.grupo2.copeteandroid.tragos.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {
    override suspend fun getTragosList(tragoName: String): Resource<List<Drink>> {
        return dataSource.getTragoByName(tragoName)
    }


}