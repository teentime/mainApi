package kr.teentime.mainApi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;


@Data @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostWriteDto {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    private boolean isAnon;
}
