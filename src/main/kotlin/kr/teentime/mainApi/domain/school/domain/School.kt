package kr.teentime.mainApi.domain.school.domain

import kr.teentime.mainApi.domain.member.persistence.entity.MemberEntity
import kr.teentime.mainApi.global.entity.constant.GenericStatus

class School (
    var code: String,
    var name: String,
    var prefix: String,
    var status: GenericStatus,
    var members: ArrayList<MemberEntity>
)