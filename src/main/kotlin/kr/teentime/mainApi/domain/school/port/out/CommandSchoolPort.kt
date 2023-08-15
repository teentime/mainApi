package kr.teentime.mainApi.domain.school.port.out

import kr.teentime.mainApi.domain.school.domain.School

fun interface CommandSchoolPort {
    fun saveSchool(school: School)
}