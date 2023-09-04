package kr.teentime.mainApi.global.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.teentime.mainApi.global.security.port.JwtParserPort
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
        val accessToken = jwtParserPort.parseAccessToken(request)
        if (!accessToken.isNullOrBlank()) {
            val authentication = jwtParserPort.authentication(accessToken)
            SecurityContextHolder.clearContext()
            val securityContext = SecurityContextHolder.getContext()
            securityContext.authentication = authentication
        }

        filterChain.doFilter(request, response)
    }
}