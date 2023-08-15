package kr.teentime.mainApi.domain.school.adapter.out.persistence.mapper

import kr.teentime.mainApi.domain.school.adapter.out.persistence.entity.SchoolEntity
import kr.teentime.mainApi.domain.school.domain.School
import kr.teentime.mainApi.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class SchoolMapper: GenericMapper<School, SchoolEntity> {
    override fun toDomain(entity: SchoolEntity?): School? =
        entity?.let {
            School(
                code =  it.code,
                name = it.name,
                prefix = it.prefix,
                status = it.status,
                members = it.members
            )
        }

    override fun toEntity(domain: School?): SchoolEntity? =
        domain?.let {
                SchoolEntity(
                    code =  it.code,
                    name = it.name,
                    prefix = it.prefix,
                    status = it.status,
                    members = it.members
                )
            }
}