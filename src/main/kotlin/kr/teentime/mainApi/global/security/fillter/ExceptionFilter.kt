package kr.teentime.mainApi.global.security.fillter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.teentime.mainApi.global.error.ErrorCode
import kr.teentime.mainApi.global.error.exception.ForbiddenException
import kr.teentime.mainApi.global.error.exception.GenericException
import kr.teentime.mainApi.global.error.exception.UnauthorizedException
import kr.teentime.mainApi.global.response.BasicResponse
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.filter.OncePerRequestFilter

class ExceptionFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            filterChain.doFilter(request, response)
        }.onFailure { e ->
            when (e) {
                is UnauthorizedException -> exceptionToResponse(ErrorCode.UNAUTHORIZED_ERROR, response)
                is ForbiddenException -> exceptionToResponse(ErrorCode.FORBIDDEN_ERROR, response)
                is GenericException -> exceptionToResponse(e.code, response)
                is UsernameNotFoundException -> exceptionToResponse(ErrorCode.MEMBER_NOT_FOUND, response)
            }
        }
    }

    private fun exceptionToResponse(errorCode: ErrorCode, response: HttpServletResponse) {
        response.status = errorCode.code
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"
        val errorResponse = BasicResponse.error(errorCode)
        val errorResponseToJson = ObjectMapper().writeValueAsString(errorResponse)
        response.writer.write(errorResponseToJson)
    }
}