package kr.teentime.mainApi.domain;

import jakarta.persistence.*;
import kr.teentime.mainApi.domain.ManyToMany.ClubMember;
import kr.teentime.mainApi.domain.basic.BasicTime;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity @Getter
@AllArgsConstructor
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

    @Column(unique = true)
    private String email;

    @Column(nullable = false,
                unique = true)
    private String authEmail;

    @Column(nullable = false,
            unique = true,
            length = 11)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "member")
    private List<ClubMember> club;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "member")
    private List<Admin> admins;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "thumbBy")
    private List<Thumb> thumbs;

    public void addClub(Club club) {
        if (this.getClub() == null) this.club = new ArrayList<>();

        ClubMember clubMember = ClubMember.builder()
                .member(this)
                .club(club)
                .build();

        this.club.add(clubMember);
    }
}
