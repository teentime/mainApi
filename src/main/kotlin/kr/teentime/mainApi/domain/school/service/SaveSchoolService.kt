package kr.teentime.mainApi.domain.school.service

import kr.teentime.mainApi.domain.school.adapter.`in`.web.request.SaveSchoolRequest
import kr.teentime.mainApi.domain.school.adapter.out.persistence.mapper.SchoolMapper
import kr.teentime.mainApi.domain.school.adapter.out.persistence.repository.SchoolRepository
import kr.teentime.mainApi.domain.school.domain.School
import kr.teentime.mainApi.domain.school.exception.SchoolNotSavedException
import kr.teentime.mainApi.domain.school.port.`in`.SaveSchoolUseCase
import kr.teentime.mainApi.global.entity.constant.GenericStatus

class SaveSchoolService(
    private val schoolRepository: SchoolRepository,
    private val schoolMapper: SchoolMapper,
): SaveSchoolUseCase {
    override fun save(req: SaveSchoolRequest) {
        val prefix = req.host.split("www.")[1]

        val schoolDomain = req.let {
            School(
                code = it.code,
                prefix = prefix,
                name = it.name,
                status = GenericStatus.ACTIVATE,
                members = ArrayList()
            )
        }

        val toEntity = schoolMapper.toEntity(schoolDomain) ?: throw SchoolNotSavedException()

        schoolRepository.save(toEntity)
    }
}