package cl.grupo2.copeteandroid.autentificacion.login.domain

class LoginUserPassUseCase(
    private val repository: AutentificacionRepository
) {
    suspend fun execute (email: String, pass: String) = repository.doLogin(email,pass)
}