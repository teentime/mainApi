package kr.teentime.mainApi.domain.member.adapter.`in`.web.request

data class SaveMemberRequest (
    var nickname: String,
    var password: String,
    var pNumber: String,
    var sEmail: String,
    var schoolId: String,
)