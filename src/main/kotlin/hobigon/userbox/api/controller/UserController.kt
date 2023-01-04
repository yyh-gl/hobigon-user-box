package hobigon.userbox.api.controller

import hobigon.userbox.api.param.CreateUserParam
import hobigon.userbox.api.view.UserView
import hobigon.userbox.usecase.user.CreateUserUseCase
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val createUserUseCase: CreateUserUseCase) {
    @PostMapping("")
    fun create(
        @RequestBody @Validated body: CreateUserParam,
    ): UserView {
        val user =
            createUserUseCase.execute(
                body.email!!,
                body.password!!,
                body.userName!!,
                body.displayName
            )
        return UserView(user.userName, user.displayName)
    }
}
