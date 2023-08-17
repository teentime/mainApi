package kr.teentime.mainApi.domain.member.adapter.out.persistence

import kr.teentime.mainApi.domain.member.port.out.MemberPort
import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.domain.member.exception.MemberNotSavedException
import kr.teentime.mainApi.domain.member.adapter.out.persistence.mapper.MemberMapper
import kr.teentime.mainApi.domain.member.adapter.out.persistence.repository.MemberRepository
import kr.teentime.mainApi.domain.member.exception.MemberNotFoundException
import org.springframework.stereotype.Repository

@Repository
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
    private val mapper: MemberMapper
): MemberPort {
    override fun save(member: Member) {
        val memberEntity = mapper.toEntity(member) ?: throw MemberNotSavedException()

        mapper.toDomain(memberRepository.save(memberEntity))
    }

    override fun queryFindById(memberId: Long): Member {

        val findById = memberRepository.findById(memberId)

        if (findById.isEmpty) throw MemberNotFoundException()

        return mapper.toDomain(findById.get())!!
    }
}