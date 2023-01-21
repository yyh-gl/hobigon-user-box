package hobigon.userbox.domain.entity.user

data class Email(val value: String) {
    init {
        if (!this.validate()) {
            throw InvalidValueException("メールアドレスの形式に誤りがあります")
        }
    }

    private fun validate(): Boolean {
        return Regex("^(.+)@(\\S+)\$").matches(this.value)
    }
}
