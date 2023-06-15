package kr.teentime.mainApi.dto.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Data @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostWriteDto {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    private boolean isAnon;

    @NotEmpty
    private String clubName;
}
