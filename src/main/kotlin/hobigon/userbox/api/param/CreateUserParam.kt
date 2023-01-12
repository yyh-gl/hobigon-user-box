package hobigon.userbox.api.param

import jakarta.validation.constraints.NotEmpty

data class CreateUserParam(
    @field:NotEmpty(message = "メールアドレスを入力してください") val email: String?,
    @field:NotEmpty(message = "パスワードを入力してください") val password: String?,
    @field:NotEmpty(message = "ユーザー名を入力してください") val userName: String?,
    val displayName: String?,
)
