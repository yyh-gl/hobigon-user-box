package hobigon.userbox.domain.entity.user

import hobigon.userbox.domain.entity.shared.InvalidValueException
import org.springframework.security.crypto.bcrypt.BCrypt

private const val MIN_LENGTH: Int = 8
private const val MAX_LENGTH: Int = 20

data class Password(val password: String) {
    private val salt: String = BCrypt.gensalt()

    init {
        if (!this.validate()) {
            throw InvalidValueException("パスワードは8文字以上、20文字以内で設定してください")
        }
    }

    private fun validate(): Boolean {
        return this.password.length in MIN_LENGTH..MAX_LENGTH
    }

    fun hash(): String {
        return BCrypt.hashpw(this.password, this.salt)
    }
}
