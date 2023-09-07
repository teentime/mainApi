package kr.teentime.mainApi.domain.member.application.service

import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.LoginRequest
import kr.teentime.mainApi.domain.member.application.port.`in`.LoginMemberUseCase
import kr.teentime.mainApi.domain.member.application.port.out.MemberPort
import kr.teentime.mainApi.domain.member.exception.MemberNotFoundException
import kr.teentime.mainApi.global.security.dto.TokenDto
import kr.teentime.mainApi.global.security.jwt.port.JwtGeneratorPort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class QueryLoginService(
    private val memberPort: MemberPort,
    private val passwordEncoder: PasswordEncoder,
    private val jwtGenerator: JwtGeneratorPort
) : LoginMemberUseCase {
    override fun execute(loginRequest: LoginRequest): TokenDto {
        val member = memberPort.login(loginRequest)

        if (!passwordEncoder.matches(loginRequest.password, member.password))
            throw MemberNotFoundException()

        val token = jwtGenerator.generate(member.id!!, member.role)

        return token
    }
}