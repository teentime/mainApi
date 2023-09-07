package kr.teentime.mainApi.domain.member.adapter.out.persistence

import kr.teentime.mainApi.domain.member.adapter.out.persistence.mapper.MemberMapper
import kr.teentime.mainApi.domain.member.adapter.out.persistence.repository.MemberRepository
import kr.teentime.mainApi.domain.member.application.port.out.MemberPort
import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.domain.member.exception.MemberDuplicatedException
import kr.teentime.mainApi.domain.member.exception.MemberNotFoundException
import kr.teentime.mainApi.domain.member.exception.MemberNotSavedException
import org.springframework.stereotype.Repository

@Repository
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
    private val mapper: MemberMapper
): MemberPort {
    override fun save(member: Member) {
        val memberEntity = mapper.toEntity(member) ?: throw MemberNotSavedException()

        val findByNicknameOrPNumber = memberRepository.findByNicknameOrPhoneNumber(member.nickname, member.pNumber)
        if (findByNicknameOrPNumber != null) throw MemberDuplicatedException()

        mapper.toDomain(memberRepository.save(memberEntity))
    }

    override fun queryFindById(memberId: Long): Member {

        val findById = memberRepository.findById(memberId)

        if (findById.isEmpty) throw MemberNotFoundException()

        return mapper.toDomain(findById.get())!!
    }
}