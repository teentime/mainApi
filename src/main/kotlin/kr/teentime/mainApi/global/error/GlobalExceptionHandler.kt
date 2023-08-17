package kr.teentime.mainApi.global.error

import kr.teentime.mainApi.global.error.dto.ErrorResponse
import kr.teentime.mainApi.global.error.exception.GenericException
import kr.teentime.mainApi.global.response.BasicResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception): ResponseEntity<ErrorResponse> {
        println(e.stackTraceToString())
        return BasicResponse.error(ErrorCode.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(GenericException::class)
    fun genericExceptionHandler(e: GenericException): ResponseEntity<ErrorResponse> =
        BasicResponse.error(e.code)
}