package kr.teentime.mainApi.dto;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateDto extends PostWriteDto {

    private Long postId;

    public Long getPostId() {
        return postId;
    }
}
