package kr.teentime.mainApi.service;

import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.domain.Post;
import kr.teentime.mainApi.repository.PostRepository;
import kr.teentime.mainApi.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void writePost(String title, String content) {
        Member loginMember = Util.getLoginMember();

        Post post = Post.builder()
                .member(loginMember)
                .title(title)
                .content(content)
                .build();

        postRepository.save(post);
    }
}