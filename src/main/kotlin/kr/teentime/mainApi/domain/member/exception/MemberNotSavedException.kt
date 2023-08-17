package kr.teentime.mainApi.domain.member.exception

import kr.teentime.mainApi.global.error.ErrorCode
import kr.teentime.mainApi.global.error.exception.GenericException

class MemberNotSavedException(
        override val code: ErrorCode = ErrorCode.MEMBER_NOT_SAVED
): GenericException(code)