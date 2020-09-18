package cl.grupo2.copeteandroid.autentificacion.registro.domain

interface RegistroRepository {
    suspend fun registrarUsuario (registroUsuario: RegistroUsuario): Boolean
}