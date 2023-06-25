package kr.teentime.mainApi.dto.club;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddReviewDto {
    
    @Size(min = 0, max = 100)
    private String content;
    
    @Size(max = 10, min = 1)
    private int star;

    private boolean isAnon;
}
