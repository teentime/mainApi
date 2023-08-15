package kr.teentime.mainApi.global.response

import kr.teentime.mainApi.global.error.ErrorCode
import org.springframework.http.ResponseEntity

data class BasicResponse<T> (
    val data: T,
    val status: Int,
) {

    companion object {
        fun error(errorInfo: ErrorCode) = ResponseEntity
            .status(errorInfo.code)
            .body(errorInfo.msg)

        fun ok(data: Any) = ResponseEntity
            .status(200)
            .body(data)

        fun created(data: Any?) = ResponseEntity
            .status(201)
            .body(data)
    }
}