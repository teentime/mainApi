package kr.teentime.mainApi.domain.member.application.facade

import kr.teentime.mainApi.domain.member.presentation.dto.request.SaveMemberRequest
import kr.teentime.mainApi.domain.member.presentation.dto.request.SearchMemberByIdRequest
import kr.teentime.mainApi.domain.member.presentation.dto.response.SaveMemberResponse
import kr.teentime.mainApi.domain.member.presentation.dto.response.SearchMemberResponse

interface MemberFacade {
    fun searchMemberById(req: SearchMemberByIdRequest): SearchMemberResponse
    fun saveMember(req: SaveMemberRequest): SaveMemberResponse
}