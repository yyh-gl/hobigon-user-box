package hobigon.userbox.domain.entity.token

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import java.util.*

class Token(val value: String) {
    companion object {
        private const val ISSUER = "hobigon"
        // FIXME: ローテーションできるようにする
        private val secretKey = System.getenv("SECRET_KEY")

        fun generate(): Token {
            val algorithm: Algorithm = Algorithm.HMAC256(secretKey)
            val jwt = JWT.create().withIssuer(ISSUER).sign(algorithm)

            return Token(value = jwt)
        }
    }

    fun verify() {
        val algorithm: Algorithm = Algorithm.HMAC256(secretKey)
        val verifier: JWTVerifier = JWT.require(algorithm).withIssuer(ISSUER).build()
        try {
            verifier.verify(value)
        } catch (e: JWTVerificationException) {
            // FIXME: e を正しくハンドリングする
            println(e)
            throw InvalidJwtException(msg = "無効なJWTです")
        }

        val jwt: DecodedJWT = JWT.decode(value)
        if (jwt.expiresAt.before(Date())) {
            throw InvalidJwtException(msg = "無効なJWTです")
        }
    }
}
