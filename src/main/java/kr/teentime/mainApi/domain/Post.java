package kr.teentime.mainApi.domain;

import jakarta.persistence.*;
import kr.teentime.mainApi.domain.basic.BasicEntity;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Builder
@Entity @Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BasicEntity {

    @Id @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;
    private Long view;

    @ManyToOne(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    private boolean isAnon;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addView() {
        this.view++;
    }

    @PrePersist
    public void persist() {
        this.isAnon = true;
        this.view = 0L;
    }
}
