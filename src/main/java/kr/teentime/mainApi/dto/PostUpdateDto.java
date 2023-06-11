package kr.teentime.mainApi.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateDto {

    @NotNull
    private Long postId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public @NotNull Long getPostId() {
        return postId;
    }
}
