package kr.teentime.mainApi.dto;

import lombok.*;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinDto {

    private String nickname;
    private String email;
    private String authEmail;
    private String password;
    private String phoneNumber;
    private Long schoolId;
}
