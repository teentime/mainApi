package kr.teentime.mainApi.domain.school.adapter.`in`.web

import kr.teentime.mainApi.domain.school.adapter.`in`.web.request.SaveSchoolRequest
import kr.teentime.mainApi.domain.school.port.`in`.SaveSchoolUseCase
import kr.teentime.mainApi.global.response.BasicResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/school")
class SchoolWebAdapter(
    private val schoolUseCase: SaveSchoolUseCase
) {

    @PostMapping("/save")
    fun saveSchool(req: SaveSchoolRequest): ResponseEntity<Any> =
            schoolUseCase.save(req).let {
                BasicResponse.created(null)
            }

}