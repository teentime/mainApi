package kr.teentime.mainApi.global.security.port

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication

interface JwtParserPort {
    fun isRefreshTokenExpired(refreshToken: String): Boolean
    fun parseRefreshToken(refreshToken: String): String?
    fun parseAccessToken(request: HttpServletRequest): String?
    fun authentication(accessToken: String): Authentication
}