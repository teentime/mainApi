package kr.teentime.mainApi.domain.member.exception

import kr.teentime.mainApi.global.error.ErrorCode
import kr.teentime.mainApi.global.error.exception.GenericException

class MemberDuplicatedException : GenericException(ErrorCode.MEMBER_DUPLICATED_ERROR) {

}
