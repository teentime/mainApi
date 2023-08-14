package kr.teentime.mainApi.global.error

import kr.teentime.mainApi.global.response.BasicResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(): ResponseEntity<String> = BasicResponse.error(ErrorCode.INTERNAL_SERVER_ERROR)
}