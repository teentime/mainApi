package kr.teentime.mainApi.service;

import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.domain.Post;
import kr.teentime.mainApi.repository.MemberRepository;
import kr.teentime.mainApi.repository.PostRepository;
import kr.teentime.mainApi.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void writePost(String title, String content, List<String> tags) {
        Member loginMember = Util.getLoginMember();
        Member member = memberRepository.findById(loginMember.getId()).get();

        // tags #a #b #c -> #a,#b,#c
        String tagString = tags.toString().trim();

        Post post = Post.builder()
                .member(member)
                .title(title)
                .content(content)
                .tags(tagString)
                .build();

        postRepository.save(post);
    }

    public Page pagingPost(Pageable page, String keyword, List<String> tag) {
        Page pagingPost = postRepository.pagingPost(page, keyword, tag);

        return pagingPost;
    }
}
