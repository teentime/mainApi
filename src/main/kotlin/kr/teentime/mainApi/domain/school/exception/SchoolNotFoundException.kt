package kr.teentime.mainApi.domain.school.exception

import kr.teentime.mainApi.global.error.ErrorCode

class SchoolNotFoundException(
    val code: ErrorCode = ErrorCode.SCHOOL_NOT_FOUND
): RuntimeException()