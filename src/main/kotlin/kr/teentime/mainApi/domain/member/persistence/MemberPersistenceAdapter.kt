package kr.teentime.mainApi.domain.member.persistence

import kr.teentime.mainApi.domain.member.application.port.MemberPort
import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.domain.member.exception.MemberNotSavedException
import kr.teentime.mainApi.domain.member.persistence.mapper.MemberMapper
import kr.teentime.mainApi.domain.member.persistence.repository.MemberRepository
import org.springframework.stereotype.Repository

@Repository
class MemberPersistenceAdapter(
        private val memberRepository: MemberRepository,
        private val mapper: MemberMapper
): MemberPort {
    override fun save(member: Member): Member? {
        val memberEntity = mapper.toEntity(member) ?: throw MemberNotSavedException()

        return mapper.toDomain(memberRepository.save(memberEntity))
    }

    override fun queryFindById(memberId: Long): Member? {

        val findById = memberRepository.findByIdOrNull(memberId)

        return mapper.toDomain(findById)
    }
}