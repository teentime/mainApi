package kr.teentime.mainApi.domain.member.presentation.dto.response

import kr.teentime.mainApi.domain.school.domain.School

data class SaveMemberResponse (
    var id: Long,
    var nickname: String,
    var pNumber: String,
    var sEmail: String,
    var school: School,
)
