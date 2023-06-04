package kr.teentime.mainApi.repository.custom;

import kr.teentime.mainApi.domain.Post;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepositoryCustom {
    Optional<List<Post>> pagingPost(Pageable page, String keyword);
}
