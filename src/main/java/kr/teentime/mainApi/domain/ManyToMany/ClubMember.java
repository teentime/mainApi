package kr.teentime.mainApi.domain.ManyToMany;

import jakarta.persistence.*;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Member;
import lombok.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubMember {

    @Id @Column(name = "club_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id")
    private Club club;
}
