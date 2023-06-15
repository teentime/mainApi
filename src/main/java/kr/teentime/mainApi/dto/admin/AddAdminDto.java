package kr.teentime.mainApi.dto.admin;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddAdminDto {

    private String memberEmail;
    private String clubName;
}
