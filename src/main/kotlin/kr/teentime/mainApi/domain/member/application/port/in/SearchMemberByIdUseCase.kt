package kr.teentime.mainApi.domain.member.application.port.`in`

import kr.teentime.mainApi.domain.member.domain.Member
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Component
fun interface SearchMemberByIdUseCase{
    fun execute(memberId: Long): Member
}