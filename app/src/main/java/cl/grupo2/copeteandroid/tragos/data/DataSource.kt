package cl.grupo2.copeteandroid.tragos.data

import cl.grupo2.copeteandroid.tragos.data.model.Drink
import cl.grupo2.copeteandroid.tragos.vo.Resource
import cl.grupo2.copeteandroid.tragos.vo.RetrofitClient

class DataSource() {

    suspend fun getTragoByName (tragoName : String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webService.getTragoByName(tragoName).drinkList)
    }

}