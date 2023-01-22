package hobigon.userbox.api.security.config

import hobigon.userbox.api.security.filter.JWTAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        authenticationManager: AuthenticationManager,
    ): SecurityFilterChain {
        http.authorizeHttpRequests {
            it.requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
            it.requestMatchers(HttpMethod.POST, "/api/v1/auth/token").permitAll()
            it.anyRequest().authenticated()
        }

        http.csrf().disable()

        http.addFilter(JWTAuthorizationFilter(authenticationManager))

        //        http.addFilterBefore(JWTAuthorizationFilter(),
        // BasicAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun authenticationManager(
        authenticationConfiguration: AuthenticationConfiguration
    ): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }
}
