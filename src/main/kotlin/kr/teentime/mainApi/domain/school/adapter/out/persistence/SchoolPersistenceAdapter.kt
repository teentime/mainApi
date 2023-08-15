package kr.teentime.mainApi.domain.school.adapter.out.persistence

import kr.teentime.mainApi.domain.school.adapter.out.persistence.mapper.SchoolMapper
import kr.teentime.mainApi.domain.school.adapter.out.persistence.repository.SchoolRepository
import kr.teentime.mainApi.domain.school.domain.School
import kr.teentime.mainApi.domain.school.exception.SchoolNotSavedException
import kr.teentime.mainApi.domain.school.port.out.CommandSchoolPort

class SchoolPersistenceAdapter(
    private val schoolMapper: SchoolMapper,
    private val schoolRepository: SchoolRepository,
): CommandSchoolPort {
    override fun saveSchool(school: School) {
        val schoolEntity = schoolMapper.toEntity(school) ?: throw SchoolNotSavedException()

        schoolRepository.save(schoolEntity)
    }
}