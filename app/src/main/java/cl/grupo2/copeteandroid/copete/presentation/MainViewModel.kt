package cl.grupo2.copeteandroid.copete.presentation

import androidx.lifecycle.*
import cl.grupo2.copeteandroid.copete.domain.AgregarAFavoritosUseCase
import cl.grupo2.copeteandroid.copete.domain.Copete
import cl.grupo2.copeteandroid.copete.domain.CopeteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val copeteRepository: CopeteRepository,
                    private val agregarAFavoritosUseCase: AgregarAFavoritosUseCase ): ViewModel(){

    private val tragosData = MutableLiveData<String>()
    private val favoritosData = MutableLiveData<Boolean>()

    fun getFavoritosData() = favoritosData

    fun setCopete(copeteName:String){
        tragosData.value = copeteName
    }

    init {
        setCopete("")
    }

    val fetchTragosList = tragosData.switchMap {nombreCopete ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(copeteRepository.getCopeteList(nombreCopete))
            }catch (e : Exception){
                emit(Resource.Failure(e))
            }
        }
    }

    fun addAFavoritos(copete: Copete){
       viewModelScope.launch {
           try {
               val result = agregarAFavoritosUseCase.execute(copete)
               handleResult(result)

           }catch (e : Exception){
               handleError(e)
           }
       }
    }

    private fun handleResult(result: Boolean) {
        favoritosData.postValue(result)
    }

    private fun handleError(e: Exception) {
        favoritosData.postValue(false)
    }

}