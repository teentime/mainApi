package kr.teentime.mainApi.domain.member.application.port.out

import kr.teentime.mainApi.domain.member.domain.Member

interface QueryMemberPort {

    fun save(member: Member)
    fun queryFindById(memberId: Long) : Member
}