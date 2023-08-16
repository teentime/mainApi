package kr.teentime.mainApi.domain.member.port.`in`

import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.SaveMemberRequest
import kr.teentime.mainApi.domain.member.domain.Member
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
interface SaveMemberUseCase{
    fun save(req: SaveMemberRequest)
}