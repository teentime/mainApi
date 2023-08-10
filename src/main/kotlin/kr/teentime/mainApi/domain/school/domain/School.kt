package kr.teentime.mainApi.domain.school.domain

import jakarta.persistence.*
import kr.teentime.mainApi.domain.member.domain.Member
import kr.teentime.mainApi.global.entity.constant.Status
import lombok.Builder

@Entity @Builder
class School (
    @Id @Column(name = "school_code")
    private var code: String,
    private var prefix: String,
    private var status: Status,

    @OneToMany(fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE],
        mappedBy = "school")
    private var members: ArrayList<Member>
)