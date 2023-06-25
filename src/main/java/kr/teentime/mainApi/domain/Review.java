package kr.teentime.mainApi.domain;


import jakarta.persistence.*;
import kr.teentime.mainApi.domain.basic.BasicEntity;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BasicEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String content;
    
    @Column(updatable = false, nullable = false)
    private int star;

    /**
     * if isAnon is true, wroteBy should be anonymous
     * otherwise it's nickname
     */
    @Column(nullable = false)
    private boolean isAnon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @Builder
    public Review(String content, int star, boolean isAnon, Club club) {
        this.content = content;
        this.star = star;
        this.isAnon = isAnon;
        this.club = club;
    }
}
