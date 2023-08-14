package kr.teentime.mainApi.domain.member.persistence.entity


import jakarta.persistence.*
import kr.teentime.mainApi.domain.school.domain.School

@Entity
@Table(name = "member")
class MemberEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "member_id")
        var id: Long,

        @Column(nullable = false,
                length = 10,
                unique = true)
        var nickname: String,
        @Column(nullable = false,
                unique = true)
        var password: String,

        /** 전화번호 */
        @Column(length = 11,
                unique = true,
                nullable = false)
        var pNumber: String,

        /** 학교 이메일 */
        @Column(length = 11,
                unique = true,
                nullable = false)
        var sEmail: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "school_code")
        var school: School,
)