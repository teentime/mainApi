package kr.teentime.mainApi.domain.member.adapter.`in`.web

import kr.teentime.mainApi.domain.member.exception.MemberNotSavedException
import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.SaveMemberRequest
import kr.teentime.mainApi.domain.member.port.`in`.SaveMemberUseCase
import kr.teentime.mainApi.domain.member.port.`in`.SearchMemberByIdUseCase
import kr.teentime.mainApi.global.error.ErrorCode
import kr.teentime.mainApi.global.response.BasicResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberWebAdapter(
    private val saveMemberUseCase: SaveMemberUseCase,
    private val searchMemberByIdUseCase: SearchMemberByIdUseCase,
) {

    @RequestMapping("/join")
    fun join(@RequestBody joinDto: SaveMemberRequest) {
        try {
            val saveMember = saveMemberUseCase.execute(joinDto)
            BasicResponse.created(saveMember)
        } catch (e: MemberNotSavedException) {
            BasicResponse.error(ErrorCode.MEMBER_NOT_SAVED)
        }
    }

}