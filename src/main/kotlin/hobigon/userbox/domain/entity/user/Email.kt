package hobigon.userbox.domain.entity.user

import hobigon.userbox.domain.entity.shared.InvalidValueException

data class Email(val email: String) {
    init {
        if (!this.validate()) {
            throw InvalidValueException("メールアドレスの形式に誤りがあります")
        }
    }

    private fun validate(): Boolean {
        return Regex("^(.+)@(\\S+)\$").matches(this.email)
    }
}
