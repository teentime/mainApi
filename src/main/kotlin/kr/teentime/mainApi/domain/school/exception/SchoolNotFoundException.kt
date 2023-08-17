package kr.teentime.mainApi.domain.school.exception

import kr.teentime.mainApi.global.error.ErrorCode
import kr.teentime.mainApi.global.error.exception.GenericException

class SchoolNotFoundException(
    override val code: ErrorCode = ErrorCode.SCHOOL_NOT_FOUND
): GenericException(code)