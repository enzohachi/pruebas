package cl.grupo2.copeteandroid.copete.data.network

import cl.grupo2.copeteandroid.copete.domain.Copete
import cl.grupo2.copeteandroid.copete.domain.CopeteRepository
import cl.grupo2.copeteandroid.copete.presentation.Resource

class RemoteCopeteRepository() : CopeteRepository{

    override suspend fun getCopeteList(copeteName: String): Resource<List<Copete>> {
        return Resource.Success(RetrofitClient.webService.getCopeteByName(copeteName).copeteList)
    }


}