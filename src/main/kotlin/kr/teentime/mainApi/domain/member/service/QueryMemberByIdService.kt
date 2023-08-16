package kr.teentime.mainApi.domain.member.service

import kr.teentime.mainApi.domain.member.port.out.MemberPort
import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.domain.member.port.`in`.SearchMemberByIdUseCase

class QueryMemberByIdService(
    private val memberPort: MemberPort
): SearchMemberByIdUseCase {
    override fun searchById(memberId: Long): Member = memberPort.queryFindById(memberId)


}