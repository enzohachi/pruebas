package cl.grupo2.copeteandroid.data

import cl.grupo2.copeteandroid.data.model.Drink
import cl.grupo2.copeteandroid.vo.Resource
import cl.grupo2.copeteandroid.vo.RetrofitClient

class DataSource() {

    suspend fun getTragoByName (tragoName : String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webService.getTragoByName(tragoName).drinkList)
    }

}