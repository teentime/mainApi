package kr.teentime.mainApi.dto.club;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FindClubDto {

    private String name;
    private String intro;
    private double starAvg;
    private List<String> tags;

    @QueryProjection
    public FindClubDto(String name, String intro, Double star, String tags) {
        this.name = name;
        this.intro = intro;

        // 평점이 0인 경우 0점으로 처리
        this.starAvg = star == null ? 0 : star;

        // tags가 string으로 오는 것을 list 형으로 변환
        String str = tags.substring(1, tags.length() - 1); // 대괄호 제거
        List<String> list = Arrays.asList(str.split(", "));
        this.tags = list;
    }
}
