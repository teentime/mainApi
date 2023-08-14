package kr.teentime.mainApi.domain.member.application.service

import kr.teentime.mainApi.domain.member.domain.Member

fun interface CommandMemberService {

    fun save(member: Member): Member
}