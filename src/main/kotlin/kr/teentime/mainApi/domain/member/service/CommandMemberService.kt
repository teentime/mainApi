package kr.teentime.mainApi.domain.member.service

import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.SaveMemberRequest
import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.domain.member.port.`in`.SaveMemberUseCase
import kr.teentime.mainApi.domain.member.port.out.MemberPort
import kr.teentime.mainApi.domain.school.adapter.out.persistence.mapper.SchoolMapper
import kr.teentime.mainApi.domain.school.adapter.out.persistence.repository.SchoolRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CommandMemberService(
    private val memberPort: MemberPort,
    private val schoolRepository: SchoolRepository,
    private val schoolMapper: SchoolMapper,
    private val passwordEncoder: PasswordEncoder
): SaveMemberUseCase {
    override fun execute(req: SaveMemberRequest) {
        req.let {
            val encodePwd = passwordEncoder.encode(it.password)

            val member = Member(
                nickname = it.nickname,
                password = encodePwd,
                pNumber = it.pNumber,
                sEmail = it.sEmail,
                school = null,
                id = null
            )

            memberPort.save(member)
        }
    }
}