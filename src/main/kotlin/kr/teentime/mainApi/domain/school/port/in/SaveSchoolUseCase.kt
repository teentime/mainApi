package kr.teentime.mainApi.domain.school.port.`in`

import jakarta.transaction.Transactional
import kr.teentime.mainApi.domain.school.adapter.`in`.web.request.SaveSchoolRequest
import org.springframework.stereotype.Component

@Component
@Transactional
interface SaveSchoolUseCase {
    fun save(req: SaveSchoolRequest)
}