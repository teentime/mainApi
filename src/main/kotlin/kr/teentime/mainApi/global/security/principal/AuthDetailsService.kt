package kr.teentime.mainApi.global.security.principal

import kr.teentime.mainApi.domain.member.adapter.out.persistence.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthDetailsService(
    val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        if (username.isNotBlank())
            AuthDetails(
                memberRepository.findByIdOrNull(username.toLong()) ?:
                throw UsernameNotFoundException("아이디 혹은 비밀번호가 없습니다."))
        else throw UsernameNotFoundException("로그인 정보가 없습니다.")
}