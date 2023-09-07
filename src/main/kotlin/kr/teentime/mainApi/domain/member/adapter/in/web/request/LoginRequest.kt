package kr.teentime.mainApi.domain.member.adapter.`in`.web.request

import org.jetbrains.annotations.NotNull

data class LoginRequest (
    @NotNull
    val phoneNumber: String,
    @NotNull
    val password: String
)
