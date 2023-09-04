package kr.teentime.mainApi.global.security.adapter

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import jakarta.servlet.http.HttpServletRequest
import kr.teentime.mainApi.global.security.config.JwtProperties
import kr.teentime.mainApi.global.security.port.JwtParserPort
import kr.teentime.mainApi.global.security.principal.AuthDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key

@Component
class JwtParserAdapter(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService
) : JwtParserPort {
    override fun isRefreshTokenExpired(refreshToken: String): Boolean {
        runCatching {
            getTokenBody(refreshToken, jwtProperties.refreshSecret).subject
        }.onFailure {
            return true
        }
        return false
    }

    override fun parseRefreshToken(refreshToken: String): String? =
            refreshToken.let {
                if (it.startsWith(JwtProperties.jwtPrefix))
                    it.replace(JwtProperties.jwtPrefix, "")
                else null
            }

    override fun parseAccessToken(request: HttpServletRequest): String? =
        request.getHeader("X-Auth-Token")
            .let { it ?: return null }
            .let {
                if (it.startsWith(JwtProperties.jwtPrefix))
                    it.replace(JwtProperties.jwtPrefix, "")
                else null
            }

    override fun authentication(accessToken: String): Authentication =
        authDetailsService.loadUserByUsername(getTokenBody(accessToken, jwtProperties.accessSecret).subject)
            .let { UsernamePasswordAuthenticationToken(it, "", it.authorities) }

    private fun getTokenBody(token: String, secret: Key): Claims =
        Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJwt(token)
            .body
}