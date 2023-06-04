package kr.teentime.mainApi.dto.dslDto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberLoginDto {

    private String password;
    private String phoneNumber;

    @QueryProjection
    public MemberLoginDto(String password, String phoneNumber) {
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
