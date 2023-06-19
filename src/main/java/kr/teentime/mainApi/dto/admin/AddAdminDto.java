package kr.teentime.mainApi.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddAdminDto {

    @NotEmpty
    @JsonProperty("nick_name")
    private String nickName;

    @NotEmpty
    @JsonProperty("club_name")
    private String clubName;
}
