package kr.teentime.mainApi.dto.club;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddClubDto {

    @NotEmpty
    private String clubName;

    @NotEmpty
    private String intro;

    @NotEmpty
    private List<String> tags;
}
