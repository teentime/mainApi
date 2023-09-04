package kr.teentime.mainApi.global.security.principal

import kr.teentime.mainApi.domain.member.adapter.out.persistence.repository.MemberRepository
import kr.teentime.mainApi.domain.member.exception.MemberNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthDetailsService(
    val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        AuthDetails(
            memberRepository.findByIdOrNull(username.toLong()) ?:
            throw MemberNotFoundException())
}