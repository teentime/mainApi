package kr.teentime.mainApi.global.error

enum class ErrorCode(
    val msg: String,
    val code: Int
) {
    MEMBER_NOT_FOUND("유저를 찾을 수 없습니다.", 404),
    MEMBER_NOT_SAVED("회원가입에 실패했습니다.", 400),

    SCHOOL_NOT_SAVED("학교 등록에 실패했습니다.", 400),
    SCHOOL_NOT_FOUND("학교를 찾을 수 없습니다.", 404),

    INTERNAL_SERVER_ERROR("서버 에러!", 500),
}