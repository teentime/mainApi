package kr.teentime.mainApi.domain.member.presentation.dto.request

data class SaveMemberRequest (
    var nickname: String,
    var password: String,
    var pNumber: String,
    var sEmail: String,
    var schoolId: Int,
)