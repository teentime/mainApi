package kr.teentime.mainApi.domain.member.domain

import kr.teentime.mainApi.domain.school.domain.School

data class Member(
    private var id: Long,
    private var nickname: String,
    private var password: String,
    private var pNumber: String,
    private var sEmail: String,
    private var school: School,
)