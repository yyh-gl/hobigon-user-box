package hobigon.userbox.domain.entity.user

import hobigon.userbox.domain.entity.shared.InvalidValueException
import org.springframework.security.crypto.bcrypt.BCrypt

data class Password(val password: String) {
    private val minLength: Int = 8
    private val maxLength: Int = 20
    private val salt: String = BCrypt.gensalt()

    init {
        if (!this.validate()) {
            throw InvalidValueException("パスワードは8文字以上、20文字以内で設定してください")
        }
    }

    private fun validate(): Boolean {
        return this.password.length in minLength..maxLength
    }

    fun hash(): String {
        return BCrypt.hashpw(this.password, this.salt)
    }
}
