package kr.teentime.mainApi.domain.member.adapter.out.persistence.mapper

import kr.teentime.mainApi.domain.member.adapter.out.persistence.entity.MemberEntity
import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.domain.school.adapter.out.persistence.mapper.SchoolMapper
import kr.teentime.mainApi.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class MemberMapper(
    private val schoolMapper: SchoolMapper
): GenericMapper<Member, MemberEntity> {
    override fun toDomain(entity: MemberEntity?): Member? =
        entity?.let {
            Member(
                    id = it.id,
                    nickname = it.nickname,
                    password = it.password,
                    pNumber = it.phoneNumber,
                    sEmail = it.sEmail,
                    role = it.role,
                    school = schoolMapper.toDomain(it.school)
            )
        }

    override fun toEntity(domain: Member?): MemberEntity? =
            domain?.let {
                MemberEntity(
                        id = it.id,
                        nickname = it.nickname,
                        password = it.password,
                        phoneNumber = it.pNumber,
                        sEmail = it.sEmail,
                        role = it.role,
                        school = schoolMapper.toEntity(it.school)
                )
            }
}