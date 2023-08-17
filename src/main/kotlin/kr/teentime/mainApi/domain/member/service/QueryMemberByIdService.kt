package kr.teentime.mainApi.domain.member.service

import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.domain.member.port.`in`.SearchMemberByIdUseCase
import kr.teentime.mainApi.domain.member.port.out.MemberPort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class QueryMemberByIdService(
    private val memberPort: MemberPort
): SearchMemberByIdUseCase {
    override fun execute(memberId: Long): Member = memberPort.queryFindById(memberId)


}