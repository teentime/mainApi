package kr.teentime.mainApi.global.security.fillter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.teentime.mainApi.domain.member.exception.MemberNotFoundException
import kr.teentime.mainApi.global.security.jwt.port.JwtParserPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(
    private val jwtParserPort: JwtParserPort
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val accessToken = jwtParserPort.parseAccessToken(request)
            if (!accessToken.isNullOrBlank()) {
                val authentication = jwtParserPort.authentication(accessToken)
                SecurityContextHolder.clearContext()
                val securityContext = SecurityContextHolder.getContext()
                securityContext.authentication = authentication
            }

            filterChain.doFilter(request, response)
        } catch (e: MemberNotFoundException) {
            response.sendError(e.code.code, e.code.msg)
        }
    }
}