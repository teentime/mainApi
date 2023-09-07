package kr.teentime.mainApi.global.security.dto

data class TokenDto (
    val accessToken: String,
    val refreshToken: String
)