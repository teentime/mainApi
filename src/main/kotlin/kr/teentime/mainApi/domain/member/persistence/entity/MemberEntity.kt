package kr.teentime.mainApi.domain.member.persistence.entity


import jakarta.persistence.*
import kr.teentime.mainApi.domain.school.domain.School

@Entity
@Table(name = "member")
class MemberEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "member_id")
        private var id: Long,

        @Column(nullable = false,
                length = 10,
                unique = true)
        private var nickname: String,
        @Column(nullable = false,
                unique = true)
        private var password: String,

        /** 전화번호 */
        @Column(length = 11,
                unique = true,
                nullable = false)
        private var pNumber: String,

        /** 학교 이메일 */
        @Column(length = 11,
                unique = true,
                nullable = false)
        private var sEmail: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "school_code")
        private var school: School,
)