package kr.teentime.mainApi.domain.member.exception

class MemberNotFoundException(
    val msg: String = "유저를 찾지 못했습니다.",
    val status: Int = 404
): RuntimeException() {
}