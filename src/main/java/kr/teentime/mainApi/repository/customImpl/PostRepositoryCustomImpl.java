package kr.teentime.mainApi.repository.customImpl;

import kr.teentime.mainApi.domain.Post;
import kr.teentime.mainApi.repository.custom.PostRepositoryCustom;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    @Override
    public Optional<List<Post>> pagingPost(Pageable page, String keyword) {


        return Optional.empty();
    }
}
