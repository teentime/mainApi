package kr.teentime.mainApi.domain.member.domain

import kr.teentime.mainApi.domain.member.domain.constant.MemberRole
import kr.teentime.mainApi.domain.school.domain.School
import lombok.AllArgsConstructor
import lombok.Builder

@Builder
@AllArgsConstructor
data class Member(
    var id: Long?,
    var nickname: String,
    var password: String,
    var pNumber: String,
    var sEmail: String,
    var role: MemberRole,
    var school: School?,
)