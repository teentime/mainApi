package kr.teentime.mainApi.domain.member.exception

import kr.teentime.mainApi.global.error.ErrorCode

class MemberNotFoundException(
    val code: ErrorCode = ErrorCode.MEMBER_NOT_FOUND
): RuntimeException()