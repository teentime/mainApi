package kr.teentime.mainApi.domain.school.adapter.out.persistence.entity

import jakarta.persistence.*
import kr.teentime.mainApi.domain.member.adapter.out.persistence.entity.MemberEntity
import kr.teentime.mainApi.global.entity.constant.GenericStatus

@Entity
@Table(name = "school")
class SchoolEntity (
    @Id @Column(name = "school_code") var code: String,

    @Column(
        unique = true,
        nullable = false)
    var prefix: String,

    @Enumerated(EnumType.STRING)
    var status: GenericStatus,

    var name: String,

    @OneToMany(fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE],
        mappedBy = "school")
    var members: ArrayList<MemberEntity>
)