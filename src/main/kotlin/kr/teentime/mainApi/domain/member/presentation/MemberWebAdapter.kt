package kr.teentime.mainApi.domain.member.presentation

import kr.teentime.mainApi.domain.member.application.facade.MemberFacade
import kr.teentime.mainApi.domain.member.exception.MemberNotSavedException
import kr.teentime.mainApi.domain.member.presentation.dto.request.SaveMemberRequest
import kr.teentime.mainApi.global.error.ErrorCode
import kr.teentime.mainApi.global.response.BasicResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberWebAdapter(
    private val memberFacade: MemberFacade
) {

    @RequestMapping("/join")
    fun join(@RequestBody joinDto: SaveMemberRequest) {
        try {
            val saveMember = memberFacade.saveMember(joinDto)
            BasicResponse.created(saveMember)
        } catch (e: MemberNotSavedException) {
            BasicResponse.error(ErrorCode.MEMBER_NOT_SAVED)
        }
    }

}