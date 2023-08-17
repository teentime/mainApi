package kr.teentime.mainApi.domain.member.adapter.`in`.web

import kr.teentime.mainApi.domain.member.exception.MemberNotSavedException
import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.SaveMemberRequest
import kr.teentime.mainApi.domain.member.port.`in`.SaveMemberUseCase
import kr.teentime.mainApi.domain.member.port.`in`.SearchMemberByIdUseCase
import kr.teentime.mainApi.global.error.ErrorCode
import kr.teentime.mainApi.global.response.BasicResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
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

    @PostMapping("/join")
    fun join(@RequestBody joinDto: SaveMemberRequest) =
            saveMemberUseCase.execute(joinDto).let {
                BasicResponse.created(it)
            }

    @GetMapping("/:id")
    fun searchById(@RequestParam memberId: Long) =
        searchMemberByIdUseCase.execute(memberId)
}