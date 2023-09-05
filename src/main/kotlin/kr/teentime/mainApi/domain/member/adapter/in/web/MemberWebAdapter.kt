package kr.teentime.mainApi.domain.member.adapter.`in`.web

import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.SaveMemberRequest
import kr.teentime.mainApi.domain.member.application.port.`in`.SaveMemberUseCase
import kr.teentime.mainApi.domain.member.application.port.`in`.SearchMemberByIdUseCase
import kr.teentime.mainApi.global.response.BasicResponse
import org.springframework.web.bind.annotation.*

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