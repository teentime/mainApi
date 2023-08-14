package kr.teentime.mainApi.domain.member.application.service

import kr.teentime.mainApi.domain.member.application.port.MemberPort
import kr.teentime.mainApi.domain.member.domain.Member

class QueryMemberServiceImpl(
    private val memberPort: MemberPort
): QueryMemberService {
    override fun queryMemberById(memberId: Long): Member? = memberPort.queryFindById(memberId)


}