package kr.teentime.mainApi.domain;

import jakarta.persistence.*;
import kr.teentime.mainApi.domain.basic.BasicEntity;
import lombok.*;

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
    private Long view = 0L;

    @ManyToOne(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
