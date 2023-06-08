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
    private String tags;
    private Long view = 0L;

    @ManyToOne(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean isAnon;

    public List<String> getTags() {
        return Arrays.stream(tags.split(",")).toList();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTags(List<String> tags) {
        this.tags = tags.toString().trim();
    }

    public void addView() {
        this.view++;
    }
}
