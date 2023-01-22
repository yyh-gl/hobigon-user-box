package hobigon.userbox.api.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager) :
    BasicAuthenticationFilter(authenticationManager) {

    companion object {
        const val TOKEN_SCHEME_PREFIX = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        //        val token =
        // Token(request.getHeader(HttpHeaders.AUTHORIZATION).removePrefix(TOKEN_SCHEME_PREFIX))
        //
        //        println("===================")
        //        println(token.verify())
        //        println("===================")
        println("===================")
        println("jwt")
        println("===================")

        filterChain.doFilter(request, response)
    }
}
