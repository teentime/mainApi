package kr.teentime.mainApi.repository.custom;

import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.post.PostPagingDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryCustom {
    PagingDto<PostPagingDto> pagingPost(Pageable page, String keyword, String clubName);
}
