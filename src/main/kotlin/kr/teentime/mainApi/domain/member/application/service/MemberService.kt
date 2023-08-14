package kr.teentime.mainApi.domain.member.application.service

import org.springframework.stereotype.Component

@Component
interface MemberService: QueryMemberService, CommandMemberService {
}