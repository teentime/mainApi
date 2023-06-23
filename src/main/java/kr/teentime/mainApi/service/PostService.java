package kr.teentime.mainApi.service;

import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.domain.Post;
import kr.teentime.mainApi.dto.post.PostUpdateDto;
import kr.teentime.mainApi.dto.post.PostWriteDto;
import kr.teentime.mainApi.exception.NotFoundClubException;
import kr.teentime.mainApi.exception.PostNotFoundException;
import kr.teentime.mainApi.repository.ClubRepository;
import kr.teentime.mainApi.repository.MemberRepository;
import kr.teentime.mainApi.repository.PostRepository;
import kr.teentime.mainApi.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;

    public Long writePost(PostWriteDto postWriteDto) throws NotFoundClubException {
        Member loginMember = Util.getLoginMember();
        Member member = memberRepository.findById(loginMember.getId()).get();
        Optional<Club> club = clubRepository.findByName(postWriteDto.getClubName());

        if (club.isEmpty()) throw new NotFoundClubException();

        Post post = Post.builder()
                .member(member)
                .club(club.get())
                .title(postWriteDto.getTitle())
                .content(postWriteDto.getContent())
                .isAnon(postWriteDto.isAnon())
                .build();

        Post save = postRepository.save(post);

        return save.getId();
    }

    public Page pagingPost(Pageable page, String keyword, String clubName) {
        Page pagingPost = postRepository.pagingPost(page, keyword, clubName);

        return pagingPost;
    }

    public void updatePost(PostUpdateDto postUpdateDto) throws PostNotFoundException {
        Optional<Post> post = postRepository.findById(postUpdateDto.getPostId());
        if (post.isEmpty()) throw new PostNotFoundException();

        post.get().setContent(postUpdateDto.getContent());
        post.get().setTitle(postUpdateDto.getTitle());
    }

    public void deletePost(Long postId) throws PostNotFoundException {

        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) throw new PostNotFoundException();
        Member member = Util.getLoginMember();

        if (!Objects.equals(post.get().getMember().getId(), member.getId())) {
            throw new IllegalAccessError("not a post's owner");
        }

        postRepository.delete(post.get());
    }
}
