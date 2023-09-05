package kr.teentime.mainApi.domain.member.application.port.`in`

import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.SaveMemberRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
fun interface SaveMemberUseCase{
    fun execute(req: SaveMemberRequest)
}