package hobigon.userbox.domain.entity.user

class User(
    val id: ID = ID(),
    val email: Email,
    val password: Password,
    val userName: String,
    var displayName: String
) {
    init {
        if (displayName === "") {
            this.displayName = this.userName
        }
    }
}
