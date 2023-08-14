package kr.teentime.mainApi.domain.member.application.facade

import kr.teentime.mainApi.domain.member.application.usecase.SaveMemberUseCase
import kr.teentime.mainApi.domain.member.application.usecase.SearchMemberByIdUseCase
import kr.teentime.mainApi.domain.member.presentation.dto.request.SaveMemberRequest
import kr.teentime.mainApi.domain.member.presentation.dto.request.SearchMemberByIdRequest
import kr.teentime.mainApi.domain.member.presentation.dto.response.SaveMemberResponse
import kr.teentime.mainApi.domain.member.presentation.dto.response.SearchMemberResponse

class MemberFacadeImpl(
    private val searchMemberByIdUseCase: SearchMemberByIdUseCase,
    private val saveMemberUseCase: SaveMemberUseCase
): MemberFacade {
    override fun searchMemberById(req: SearchMemberByIdRequest): SearchMemberResponse =
        searchMemberByIdUseCase.execute(req.memberId).let {
            SearchMemberResponse(it.nickname, it.pNumber, it.sEmail)
        }

    override fun saveMember(req: SaveMemberRequest): SaveMemberResponse {
        TODO()
    }

}