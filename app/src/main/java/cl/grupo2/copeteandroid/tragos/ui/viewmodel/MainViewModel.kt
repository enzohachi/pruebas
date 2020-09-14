package cl.grupo2.copeteandroid.tragos.ui.viewmodel

import androidx.lifecycle.*
import cl.grupo2.copeteandroid.tragos.domain.Repo
import cl.grupo2.copeteandroid.tragos.vo.Resource
import kotlinx.coroutines.Dispatchers

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