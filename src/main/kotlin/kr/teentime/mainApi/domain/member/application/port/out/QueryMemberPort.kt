package kr.teentime.mainApi.domain.member.application.port.out

import kr.teentime.mainApi.domain.member.domain.Member

fun interface QueryMemberPort {

    fun queryFindById(memberId: Long) : Member
}