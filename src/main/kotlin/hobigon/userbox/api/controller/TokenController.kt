package hobigon.userbox.api.controller

import hobigon.userbox.api.view.TokenView
import hobigon.userbox.usecase.auth.CreateTokenUseCase
import java.util.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth/token")
class TokenController(private val createTokenUseCase: CreateTokenUseCase) {
    @PostMapping("")
    fun create(
        @RequestHeader(name = "Authorization", required = true) authorization: String,
    ): TokenView {
        val credential = authorization.split("Basic ")[1]
        val decodedAuthorization =
            Base64.getDecoder().decode(credential.toByteArray()).toString(Charsets.UTF_8)
        val splitAuthorization = decodedAuthorization.split(":")
        val email = splitAuthorization[0]
        val password = splitAuthorization[1]

        val jwt = createTokenUseCase.execute(email, password)

        return TokenView(jwt = jwt)
    }
}
