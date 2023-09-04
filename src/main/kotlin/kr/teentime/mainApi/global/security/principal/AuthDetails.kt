package kr.teentime.mainApi.global.security.principal

import kr.teentime.mainApi.domain.member.adapter.out.persistence.entity.MemberEntity
import kr.teentime.mainApi.domain.member.domain.constant.MemberRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails(
    private val member: MemberEntity
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        val roles = ArrayList<GrantedAuthority>()

        roles.add(SimpleGrantedAuthority(MemberRole.STUDENT.toString()))

        return roles
    }

    override fun getPassword(): String? = null

    override fun getUsername(): String = member.id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}