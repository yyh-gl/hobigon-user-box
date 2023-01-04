package hobigon.userbox.usecase.user

import hobigon.userbox.domain.entity.user.Email
import hobigon.userbox.domain.entity.user.Password
import hobigon.userbox.domain.entity.user.User
import org.springframework.stereotype.Service

@Service
class CreateUserUseCase {
    fun execute(email: String, password: String, userName: String, displayName: String?): User {
        return User(
            email = Email(email),
            password = Password(password),
            userName = userName,
            displayName = displayName ?: ""
        )
    }
}
