package kr.teentime.mainApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 기본 검색 DTO
 * @param <T> 검색하는 클래스
 */
@Data
@AllArgsConstructor
@Builder
public class PagingDto<T> {

    List<T> items;
    long totalPage;
    long totalElement;
}
