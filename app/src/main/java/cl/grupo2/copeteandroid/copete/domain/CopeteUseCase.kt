package cl.grupo2.copeteandroid.copete.domain

import cl.grupo2.copeteandroid.copete.data.network.RemoteCopeteRepository
import cl.grupo2.copeteandroid.copete.presentation.Resource

class CopeteUseCase(private val remoteCopeteRepository: RemoteCopeteRepository) : CopeteRepository {
    override suspend fun getCopeteList(copeteName: String): Resource<List<Copete>> {
        return remoteCopeteRepository.getCopeteList(copeteName)
    }
}