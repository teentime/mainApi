package kr.teentime.mainApi.domain.member.persistence.repository

import kr.teentime.mainApi.domain.member.persistence.entity.MemberEntity
import org.springframework.data.repository.CrudRepository

interface MemberRepository : CrudRepository<MemberEntity, Long> {
    fun findByIdOrNull(memberId: Long): MemberEntity?

}