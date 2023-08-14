package kr.teentime.mainApi.domain.member.application.usecase

import kr.teentime.mainApi.domain.member.application.service.MemberService
import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.domain.member.exception.MemberNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class SearchMemberByIdUseCase(
    private val memberService: MemberService
) {
    fun execute(memberId: Long): Member =
        memberService.queryMemberById(memberId) ?: throw MemberNotFoundException()
}