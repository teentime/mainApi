package kr.teentime.mainApi.global.error.exception

import kr.teentime.mainApi.global.error.ErrorCode

open class GenericException(open val code: ErrorCode): RuntimeException() {
}