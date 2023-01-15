package hobigon.userbox.usecase.auth

import hobigon.userbox.domain.entity.shared.ForbiddenException
import hobigon.userbox.domain.entity.shared.InvalidValueException
import hobigon.userbox.domain.entity.token.Token
import hobigon.userbox.domain.entity.user.Email
import hobigon.userbox.domain.entity.user.Password
import hobigon.userbox.domain.repository.UserRepository
import hobigon.userbox.domain.service.AuthorizeService
import org.springframework.stereotype.Service

@Service
class CreateTokenUseCase(
    private val userRepository: UserRepository,
    private val authorizeService: AuthorizeService
) {
    @Suppress("SwallowedException")
    fun execute(email: String, plainPassword: String): String {
        try {
            val hashedPassword = userRepository.findHashedPasswordByEmail(Email(email))

            if (!authorizeService.authorize(Password(plainPassword), hashedPassword)) {
                throw ForbiddenException(msg = "メールアドレスまたはパスワードに誤りがあります")
            }
        } catch (e: InvalidValueException) {
            throw ForbiddenException(msg = "メールアドレスまたはパスワードに誤りがあります")
        }

        return Token().toString()
    }
}
