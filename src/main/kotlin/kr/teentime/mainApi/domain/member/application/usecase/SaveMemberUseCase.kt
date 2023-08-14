package kr.teentime.mainApi.domain.member.application.usecase

import kr.teentime.mainApi.domain.member.application.service.MemberService
import kr.teentime.mainApi.domain.member.domain.Member
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class SaveMemberUseCase(
    private val memberService: MemberService
) {

    fun execute(member: Member): Member =
        memberService.save(member)
}