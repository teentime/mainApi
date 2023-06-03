package kr.teentime.mainApi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {

    /**
     * 전화번호 or 개인 이메일
     */
    @NotEmpty @Size(min = 5)
    private String loginId;

    @NotEmpty @Size(min = 8)
    private String password;
}
