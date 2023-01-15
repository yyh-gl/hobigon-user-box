package hobigon.userbox.domain.entity.token

// FIXME: JWT生成処理を実装
data class Token(val value: String = "hogehoge") {
    override fun toString(): String {
        return this.value
    }
}
