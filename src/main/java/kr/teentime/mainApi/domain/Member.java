package kr.teentime.mainApi.domain;

import jakarta.persistence.*;
import kr.teentime.mainApi.domain.basic.BasicTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BasicTime {

    @Id
    @Column(name = "member_id",
        nullable = false,
        updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,
            unique = true)
    private String nickName;

    @Column(nullable = false,
            unique = true)
    private String email;

    @Column(nullable = false,
            unique = true,
            length = 11)
    private String phoneNumber;
}
