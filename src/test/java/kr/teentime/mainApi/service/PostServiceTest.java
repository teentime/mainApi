package kr.teentime.mainApi.service;

import jakarta.persistence.EntityManager;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.domain.Post;
import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.post.PostPagingDto;
import kr.teentime.mainApi.dto.post.PostWriteDto;
import kr.teentime.mainApi.exception.ClubNotFoundException;
import kr.teentime.mainApi.exception.PostNotFoundException;
import kr.teentime.mainApi.repository.ClubRepository;
import kr.teentime.mainApi.repository.MemberRepository;
import kr.teentime.mainApi.repository.PostRepository;
import kr.teentime.mainApi.repository.ThumbRepository;
import kr.teentime.mainApi.testConfig.WithMockCustomUser;
import kr.teentime.mainApi.util.Util;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Transactional()
@ActiveProfiles("test")
@Rollback
@WithMockCustomUser
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ThumbRepository thumbRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("포스트 작성")
    void createPost() throws ClubNotFoundException {
        // given
        String title = "test";
        String content = "content";

        PostWriteDto post = PostWriteDto.builder()
                .title(title)
                .content(content)
                .clubName("test")
                .isAnon(true)
                .build();

        // when
        postService.writePost(post);
        
        em.flush();
        em.clear();

        List<Post> posts = postRepository.findAll();

        // then
        assertNotNull(posts);
        Assertions.assertThat(posts.get(0).getContent())
                .isEqualTo(content);
    }

    @Test
    @DisplayName("포스트 가져오기 - 기본")
    void pagingPost() {
        // given
        String title = "test";
        String content = "content";

        Optional<Club> club = clubRepository.findByName("test");

        Post post = Post.builder()
                .title(title)
                .content(content)
                .member(Util.getLoginMember())
                .club(club.get())
                .isAnon(true)
                .build();

        postRepository.save(post);

        Pageable page = PageRequest.of(0, 20);

        // when

        PagingDto<PostPagingDto> posts = postService.pagingPost(page, "", "test");

        // then
        assertNotNull(posts.getTotalElement());
        Assertions.assertThat(posts.getItems().get(0).getTitle()).isEqualTo(title);
    }

    @Test
    @DisplayName("포스트 가져오기 - 검색")
    void searchPost() throws ClubNotFoundException {
        // given
        String title = "test";
        String content = "content";

        PostWriteDto post = PostWriteDto.builder()
                .title(title)
                .content(content)
                .clubName("test")
                .isAnon(true)
                .build();

        postService.writePost(post);

        String title2 = "second test";
        String content2 = "second content";

        PostWriteDto post2 = PostWriteDto.builder()
                .title(title2)
                .content(content2)
                .clubName("test")
                .isAnon(true)
                .build();

        postService.writePost(post2);

        Pageable page = PageRequest.of(0, 20);

        em.flush();
        em.clear();

        // when
        PagingDto<PostPagingDto> posts = postService.pagingPost(page, "second", "test");
        PagingDto<PostPagingDto> postWOclub = postService.pagingPost(page, "second", null);

        // then
        assertNotNull(posts.getTotalElement() == 1);
        Assertions.assertThat(posts.getItems().get(0).getClub()).isEqualTo("test");
        Assertions.assertThat(postWOclub.getItems().get(0).getClub()).isEqualTo("");
    }

    @Test
    @DisplayName("추천 추가 테스트")
    void thumbAddTest() throws PostNotFoundException, ClubNotFoundException {
        // given
        String title = "test";
        String content = "content";

        PostWriteDto post = PostWriteDto.builder()
                .title(title)
                .content(content)
                .clubName("test")
                .isAnon(true)
                .build();

        Long postId = postService.writePost(post);

        // when
        postService.addThumbs(postId);
        em.flush();
        em.clear();

        // then
        Member loginMember = Util.getLoginMember();
        Member member = memberRepository.findById(loginMember.getId()).get();
        Assertions.assertThat(member.getThumbs().size()).isEqualTo(1);

        Post findPost = postRepository.findById(postId).get();
        Assertions.assertThat(findPost.getThumbs().size()).isEqualTo(1);
    }
}