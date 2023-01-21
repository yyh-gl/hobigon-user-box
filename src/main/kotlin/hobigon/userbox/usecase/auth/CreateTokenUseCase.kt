package hobigon.userbox.usecase.auth

import hobigon.userbox.domain.entity.shared.FailedAuthenticationException
import hobigon.userbox.domain.entity.token.Token
import hobigon.userbox.domain.entity.user.Email
import hobigon.userbox.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CreateTokenUseCase(
    private val userRepository: UserRepository,
) {
    @Suppress("SwallowedException")
    fun execute(email: String, plainPassword: String): String {
        val mail = Email(email)

        try {
            val user = userRepository.fetchByEmail(mail)
            user.password.verify(plainPassword)
        } catch (e: Exception) {
            throw FailedAuthenticationException(msg = "メールアドレスまたはパスワードに誤りがあります")
        }

        return Token.generate().value
    }
}
