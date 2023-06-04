package kr.teentime.mainApi.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryCustom {
    Page pagingPost(Pageable page, String keyword, String tags);
}
