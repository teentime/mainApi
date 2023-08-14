package kr.teentime.mainApi.domain.member.exception

class MemberNotSavedException(
        val msg: String = "유저 등록에 실패했습니다.",
        val status: Int = 400
): RuntimeException() {
}