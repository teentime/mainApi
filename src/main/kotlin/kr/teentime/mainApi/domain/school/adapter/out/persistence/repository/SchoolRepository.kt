package kr.teentime.mainApi.domain.school.adapter.out.persistence.repository

import kr.teentime.mainApi.domain.school.adapter.out.persistence.entity.SchoolEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface SchoolRepository: JpaRepository<SchoolEntity, String> {

}