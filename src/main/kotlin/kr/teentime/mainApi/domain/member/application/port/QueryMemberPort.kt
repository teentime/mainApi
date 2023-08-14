package kr.teentime.mainApi.domain.member.application.port

import kr.teentime.mainApi.domain.member.domain.Member

interface QueryMemberPort {

    fun save(member: Member) : Member?
    fun queryFindById(memberId: Long) : Member?
}