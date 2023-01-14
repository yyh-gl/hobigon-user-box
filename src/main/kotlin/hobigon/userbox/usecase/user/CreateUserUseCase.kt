package hobigon.userbox.usecase.user

import hobigon.userbox.domain.entity.user.Email
import hobigon.userbox.domain.entity.user.Password
import hobigon.userbox.domain.entity.user.User
import hobigon.userbox.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CreateUserUseCase(val userRepository: UserRepository) {
    fun execute(email: String, password: String, userName: String, displayName: String?): User {
        val user =
            User(
                email = Email(email),
                password = Password(password),
                userName = userName,
                displayName = displayName ?: ""
            )
        userRepository.save(user)
        return user
    }
}
