package kr.teentime.mainApi.global.error.exception

import kr.teentime.mainApi.global.error.ErrorCode

class UnauthorizedException : GenericException(ErrorCode.UNAUTHORIZED_ERROR) {
}