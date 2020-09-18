package cl.grupo2.copeteandroid.autentificacion.login.domain

interface AutentificacionRepository {
    suspend fun doLogin (email: String, pass: String) : UserAuth
}