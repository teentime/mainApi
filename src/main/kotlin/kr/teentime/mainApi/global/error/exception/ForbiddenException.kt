package kr.teentime.mainApi.global.error.exception

import kr.teentime.mainApi.global.error.ErrorCode

class ForbiddenException : GenericException(ErrorCode.FORBIDDEN_ERROR) {
}