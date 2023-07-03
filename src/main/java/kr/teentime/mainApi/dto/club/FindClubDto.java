package kr.teentime.mainApi.dto.club;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FindClubDto {

    // 동아리 이름
    private String name;

    // 동아리 평점
    private double starAvg;
    private String leader;

    // 동아리 인원수
    private int memberCnt;
    private List<String> tags;

    @QueryProjection
    public FindClubDto(String name, String leader, Double star, String tags, Integer memberCnt) {
        this.name = name;
        this.leader = leader;
        this.memberCnt = memberCnt;

        // 평점이 0인 경우 0점으로 처리
        this.starAvg = star == null ? 0 : star;

        // tags가 string으로 오는 것을 list 형으로 변환
        String str = tags.substring(1, tags.length() - 1); // 대괄호 제거
        List<String> list = Arrays.asList(str.split(", "));
        this.tags = list;
    }
}
