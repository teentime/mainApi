package kr.teentime.mainApi.dto.club;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewDto {

    private int star;
    private String reviewedBy;
    private String content;
    private LocalDateTime reviewTime;
    private boolean isAnon;

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    @QueryProjection
    public ReviewDto(int star, String reviewedBy, String content, LocalDateTime reviewTime, boolean isAnon) {
        this.star = star;
        this.reviewedBy = reviewedBy;
        this.content = content;
        this.reviewTime = reviewTime;
        this.isAnon = isAnon;
    }
}
