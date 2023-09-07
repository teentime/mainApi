package kr.teentime.mainApi.domain.member.application.port.out

import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.LoginRequest
import kr.teentime.mainApi.domain.member.domain.Member

interface QueryMemberPort {

    fun queryFindById(memberId: Long) : Member

    fun login(loginRequest: LoginRequest) : Member
}