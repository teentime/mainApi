package kr.teentime.mainApi.dto;

import jakarta.validation.constraints.NotEmpty;
import kr.teentime.mainApi.domain.enums.ENUMS_clubType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddClubDto {

    @NotEmpty
    private String clubName;

    @NotEmpty
    private String clubType;
}
