package kr.teentime.mainApi.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import kr.teentime.mainApi.domain.Post;
import kr.teentime.mainApi.dto.PostWriteDto;
import kr.teentime.mainApi.dto.dslDto.PostPagingDto;
import kr.teentime.mainApi.repository.PostRepository;
import kr.teentime.mainApi.testConfig.WithMockCustomUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
    EntityManager em;

    @Test
    @DisplayName("포스트 작성")
    void createPost() {
        // given
        String title = "test";
        String content = "content";

        PostWriteDto post = PostWriteDto.builder()
                .title(title)
                .content(content)
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

        PostWriteDto post = PostWriteDto.builder()
                .title(title)
                .content(content)
                .isAnon(true)
                .build();

        Pageable page = PageRequest.of(0, 20);

        // when

        Page posts = postService.pagingPost(page, "");

        // then
        assertNotNull(posts);
    }

    @Test
    @DisplayName("포스트 가져오기 - 검색")
    void searchPost() {
        // given
        String title = "test";
        String content = "content";

        PostWriteDto post = PostWriteDto.builder()
                .title(title)
                .content(content)
                .isAnon(true)
                .build();

        postService.writePost(post);

        String title2 = "second test";
        String content2 = "second content";

        PostWriteDto post2 = PostWriteDto.builder()
                .title(title2)
                .content(content2)
                .isAnon(true)
                .build();

        postService.writePost(post2);

        Pageable page = PageRequest.of(0, 20);

        // when

        Page posts = postService.pagingPost(page, "second");

        // then
        assertNotNull(posts.getTotalElements() == 1);
    }


}