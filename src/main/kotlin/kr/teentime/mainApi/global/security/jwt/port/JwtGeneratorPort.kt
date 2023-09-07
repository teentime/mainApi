package kr.teentime.mainApi.global.security.jwt.port

import kr.teentime.mainApi.domain.member.domain.constant.MemberRole
import kr.teentime.mainApi.global.security.dto.TokenDto

fun interface JwtGeneratorPort {

    fun generate(userId: Long, role: MemberRole): TokenDto
}