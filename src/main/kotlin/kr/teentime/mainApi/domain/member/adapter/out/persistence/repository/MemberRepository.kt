package kr.teentime.mainApi.domain.member.adapter.out.persistence.repository

import kr.teentime.mainApi.domain.member.adapter.out.persistence.entity.MemberEntity
import org.springframework.data.repository.CrudRepository

interface MemberRepository : CrudRepository<MemberEntity, Long> {
    fun findByIdOrNull(memberId: Long): MemberEntity?

}