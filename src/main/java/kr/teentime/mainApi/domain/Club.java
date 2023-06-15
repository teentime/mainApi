package kr.teentime.mainApi.domain;

import jakarta.persistence.*;
import kr.teentime.mainApi.domain.ManyToMany.ClubMember;
import kr.teentime.mainApi.domain.basic.BasicEntity;
import kr.teentime.mainApi.domain.enums.ENUMS_clubType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends BasicEntity {

    @Id
    @Column(name = "club_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,
            unique = true,
            updatable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "club")
    private List<ClubMember> members;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "club")
    private List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "club")
    private List<Admin> admin = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ENUMS_clubType type;
}
