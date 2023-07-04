package kr.teentime.mainApi.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostPagingDto {

    private String title;
    private String content;
    private Long view;
    private Long replyCnt = 0L;
    private Long favoriteCnt = 0L;
    private LocalDateTime createdAt;
    private String wroteBy;
    private String club;

    @QueryProjection
    public PostPagingDto(String title, String content, Long view,
                         LocalDateTime createdAt, String wroteBy, String club) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.createdAt = createdAt;
        this.wroteBy = wroteBy;
        this.club = club;
    }
}
