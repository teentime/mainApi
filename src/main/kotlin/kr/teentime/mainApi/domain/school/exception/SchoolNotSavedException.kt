package kr.teentime.mainApi.domain.school.exception

import kr.teentime.mainApi.global.error.ErrorCode

class SchoolNotSavedException(
    val code: ErrorCode = ErrorCode.SCHOOL_NOT_SAVED
): RuntimeException()
