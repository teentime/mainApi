package kr.teentime.mainApi.domain.member.application.port.out

import kr.teentime.mainApi.domain.member.domain.Member

fun interface CommandMemberPort {
    fun save(member: Member)

}