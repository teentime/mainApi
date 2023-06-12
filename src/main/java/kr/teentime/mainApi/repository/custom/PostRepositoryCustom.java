package kr.teentime.mainApi.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositoryCustom {
    Page pagingPost(Pageable page, String keyword, String clubName);
}
