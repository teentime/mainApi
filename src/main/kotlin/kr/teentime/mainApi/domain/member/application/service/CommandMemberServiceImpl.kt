package kr.teentime.mainApi.domain.member.application.service

import kr.teentime.mainApi.domain.member.application.port.MemberPort
import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.domain.member.exception.MemberNotSavedException

class CommandMemberServiceImpl(
    private val memberPort: MemberPort
): CommandMemberService {
    override fun save(member: Member): Member = memberPort.save(member) ?: throw MemberNotSavedException()
}