package kr.teentime.mainApi.domain.member.adapter.`in`.web

import kr.teentime.mainApi.domain.member.exception.MemberNotSavedException
import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.SaveMemberRequest
import kr.teentime.mainApi.domain.member.port.`in`.MemberUseCase
import kr.teentime.mainApi.global.error.ErrorCode
import kr.teentime.mainApi.global.response.BasicResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberWebAdapter(
    private val memberUseCase: MemberUseCase
) {

    @RequestMapping("/join")
    fun join(@RequestBody joinDto: SaveMemberRequest) {
        try {
            val saveMember = memberUseCase.save(joinDto)
            BasicResponse.created(saveMember)
        } catch (e: MemberNotSavedException) {
            BasicResponse.error(ErrorCode.MEMBER_NOT_SAVED)
        }
    }

}