package kr.teentime.mainApi.global.security.jwt.adapter

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import kr.teentime.mainApi.domain.member.domain.constant.MemberRole
import kr.teentime.mainApi.global.security.dto.TokenDto
import kr.teentime.mainApi.global.security.jwt.JwtProperties
import kr.teentime.mainApi.global.security.jwt.port.JwtGeneratorPort
import java.security.Key
import java.time.LocalDateTime
import java.util.*

class JwtGeneratorAdapter(
    private val jwtProperties: JwtProperties
) : JwtGeneratorPort {
    override fun generate(userId: Long, role: MemberRole): TokenDto {
        val accessToken = generateToken(userId, role, jwtProperties.accessKey, jwtProperties.accessExp)
        val refreshToken = generateToken(userId, role, jwtProperties.refreshKey, jwtProperties.refreshExp)

        val accessExpiredAt = expiredAt(jwtProperties.accessExp)
        val refreshExpiredAt = expiredAt(jwtProperties.refreshExp)

        // Todo: refresh Token 저장 로직

        return TokenDto(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    private fun generateToken(userId: Long, role: MemberRole, secret: Key, expiredAt: Int) =
        Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS512)
            .setSubject(userId.toString())
            .claim("role", role)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiredAt * 1000))
            .compact()

    private fun expiredAt(expiredTime: Int): LocalDateTime =
        LocalDateTime.now().plusSeconds(expiredTime.toLong())
}