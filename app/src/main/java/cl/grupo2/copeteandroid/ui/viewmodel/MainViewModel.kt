package cl.grupo2.copeteandroid.ui.viewmodel

import androidx.lifecycle.*
import cl.grupo2.copeteandroid.data.model.DrinkEntity
import cl.grupo2.copeteandroid.domain.Repo
import cl.grupo2.copeteandroid.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo): ViewModel(){

    private val tragosData = MutableLiveData<String>()
    fun setTrago(tragoName:String){
        tragosData.value = tragoName
    }

    init {
        setTrago("")
    }

    val fetchTragosList = tragosData.switchMap {nombreTrago ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getTragosList(nombreTrago))
            }catch (e : Exception){
                emit(Resource.Failure(e))
            }
        }
    }

}