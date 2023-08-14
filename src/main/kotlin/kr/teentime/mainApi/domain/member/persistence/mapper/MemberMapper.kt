package kr.teentime.mainApi.domain.member.persistence.mapper

import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.domain.member.persistence.entity.MemberEntity
import kr.teentime.mainApi.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class MemberMapper: GenericMapper<Member, MemberEntity> {
    override fun toDomain(entity: MemberEntity?): Member? =
        entity?.let {
            Member(
                    id = it.id,
                    nickname = it.nickname,
                    password = it.password,
                    pNumber = it.pNumber,
                    sEmail = it.sEmail,
                    school = it.school
            )
        }

    override fun toEntity(domain: Member?): MemberEntity? =
            domain?.let {
                MemberEntity(
                        id = it.id,
                        nickname = it.nickname,
                        password = it.password,
                        pNumber = it.pNumber,
                        sEmail = it.sEmail,
                        school = it.school
                )
            }
}