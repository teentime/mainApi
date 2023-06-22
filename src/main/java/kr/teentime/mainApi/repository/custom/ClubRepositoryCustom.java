package kr.teentime.mainApi.repository.custom;

import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.club.FindClubDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepositoryCustom {

    PagingDto<FindClubDto> findByTag(String keyword, List<String> tags, Pageable pageable);
}
