package kr.teentime.mainApi.domain.member.adapter.`in`.web

import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.LoginRequest
import kr.teentime.mainApi.domain.member.adapter.`in`.web.request.SaveMemberRequest
import kr.teentime.mainApi.domain.member.application.port.`in`.LoginMemberUseCase
import kr.teentime.mainApi.domain.member.application.port.`in`.SaveMemberUseCase
import kr.teentime.mainApi.domain.member.application.port.`in`.SearchMemberByIdUseCase
import kr.teentime.mainApi.global.response.BasicResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberWebAdapter(
    private val saveMemberUseCase: SaveMemberUseCase,
    private val searchMemberByIdUseCase: SearchMemberByIdUseCase,
    private val loginMemberUseCase: LoginMemberUseCase,
) {

    @PostMapping("/join")
    fun join(@RequestBody joinDto: SaveMemberRequest) =
            saveMemberUseCase.execute(joinDto).let {
                BasicResponse.created(it)
            }

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginRequest) =
        loginMemberUseCase.execute(loginDto).let {
            val headers = HttpHeaders()
            headers.add("X-Auth-Token", it.accessToken)
            headers.add("X-Refresh-Token", it.refreshToken)

            BasicResponse.ok(
                data = "로그인 성공하였습니다.",
                headers = headers
            )
        }

    @GetMapping("/:id")
    fun searchById(@RequestParam memberId: Long) =
        searchMemberByIdUseCase.execute(memberId)
}