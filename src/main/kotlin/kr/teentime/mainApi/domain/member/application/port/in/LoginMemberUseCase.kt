package kr.teentime.mainApi.domain.member.application.port.`in`

import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.LoginRequest
import kr.teentime.mainApi.global.security.dto.TokenDto
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Transactional(readOnly = true)
@Component
fun interface LoginMemberUseCase {
    fun execute(loginRequest: LoginRequest): TokenDto
}