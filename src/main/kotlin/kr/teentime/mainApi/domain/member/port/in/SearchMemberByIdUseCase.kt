package kr.teentime.mainApi.domain.member.port.`in`

import kr.teentime.mainApi.domain.member.domain.Member
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Component
interface SearchMemberByIdUseCase{
    fun execute(memberId: Long): Member
}