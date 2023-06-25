package kr.teentime.mainApi.domain;

import jakarta.persistence.*;
import kr.teentime.mainApi.domain.ManyToMany.ClubMember;
import kr.teentime.mainApi.domain.basic.BasicEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "club")
    private List<AdminLog> log;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "club")
    private List<Review> review;

    private String intro;
    private String tags;
    private int thumb;

    public List<String> getTags() {
        String str = this.tags.substring(1, this.tags.length() - 1); // 대괄호 제거
        List<String> list = Arrays.asList(str.split(", "));

        return list;
    }

    @PrePersist
    void init() {
        this.thumb = 0;
    }
}
