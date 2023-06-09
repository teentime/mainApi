package kr.teentime.mainApi.service;

import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.domain.Post;
import kr.teentime.mainApi.dto.PostUpdateDto;
import kr.teentime.mainApi.dto.PostWriteDto;
import kr.teentime.mainApi.exception.PostNotFoundException;
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
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void writePost(PostWriteDto postWriteDto) {
        Member loginMember = Util.getLoginMember();
        Member member = memberRepository.findById(loginMember.getId()).get();

        // tags #a #b #c -> #a,#b,#c
        String tagString = postWriteDto.getTags().toString().trim();

        Post post = Post.builder()
                .member(member)
                .title(postWriteDto.getTitle())
                .content(postWriteDto.getContent())
                .isAnon(postWriteDto.isAnon())
                .tags(tagString)
                .build();

        postRepository.save(post);
    }

    public Page pagingPost(Pageable page, String keyword, List<String> tag) {
        Page pagingPost = postRepository.pagingPost(page, keyword, tag);

        return pagingPost;
    }

    public void updatePost(PostUpdateDto postUpdateDto) throws PostNotFoundException {
        Optional<Post> post = postRepository.findById(postUpdateDto.getPostId());
        if (post.isEmpty()) throw new PostNotFoundException();

        post.get().setTags(postUpdateDto.getTags());
        post.get().setContent(postUpdateDto.getContent());
        post.get().setTitle(postUpdateDto.getTitle());
    }
}
