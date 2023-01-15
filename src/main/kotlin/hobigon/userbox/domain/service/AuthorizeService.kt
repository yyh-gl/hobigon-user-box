package hobigon.userbox.domain.service

import hobigon.userbox.domain.entity.user.Password
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
object AuthorizeService {
    fun authorize(plainPassword: Password, hashedPassword: String): Boolean {
        return BCrypt.checkpw(plainPassword.value, hashedPassword)
    }
}
