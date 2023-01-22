package hobigon.userbox.api.security.filter

import hobigon.userbox.domain.entity.token.Token
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager) :
    BasicAuthenticationFilter(authenticationManager) {

    companion object {
        const val TOKEN_SCHEME_PREFIX = "Bearer "
        private val NO_AUTHORIZATION_REQUIRED_PATHS = listOf("/api/v1/users", "/api/v1/auth/token")

        private fun needsAuthorization(path: String): Boolean {
            return !NO_AUTHORIZATION_REQUIRED_PATHS.contains(path)
        }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (needsAuthorization(request.requestURI)) {
            val token =
                Token(
                    request.getHeader(HttpHeaders.AUTHORIZATION).removePrefix(TOKEN_SCHEME_PREFIX)
                )
            token.verify()
        }

        filterChain.doFilter(request, response)
    }
}
