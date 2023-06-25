package kr.teentime.mainApi.repository.custom;

import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.club.ReviewDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepositoryCustom {

    PagingDto<ReviewDto> findReview(Pageable pageable, String clubName);
}
