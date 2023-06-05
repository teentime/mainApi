package kr.teentime.mainApi.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import kr.teentime.mainApi.domain.Post;
import kr.teentime.mainApi.dto.dslDto.PostPagingDto;
import kr.teentime.mainApi.repository.PostRepository;
import kr.teentime.mainApi.testConfig.WithMockCustomUser;
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
        List<String> tags = List.of("#test");

        // when
        postService.writePost(title, content, tags);
        
        em.flush();
        em.clear();

        // then
        assertNotNull(postRepository.findAll());
    }

    @Test
    @DisplayName("포스트 가져오기 - 기본")
    void pagingPost() {
        // given
        String title = "test";
        String content = "content";
        List<String> tags = List.of("#test");
        postService.writePost(title, content, tags);

        Pageable page = PageRequest.of(0, 20);

        // when

        Page posts = postService.pagingPost(page, "", null);

        // then
        assertNotNull(posts);
    }

    @Test
    @DisplayName("포스트 가져오기 - 태그 검색")
    void searchTagPost() {
        // given


    }

    @Test
    @DisplayName("포스트 가져오기 - 검색")
    void searchPost() {
        // given
        String title = "test";
        String content = "content";
        postService.writePost(title, content, List.of());

        String title2 = "test";
        String content2 = "hello world";
        postService.writePost(title2, content2, List.of());

        Pageable page = PageRequest.of(0, 20);

        // when

        Page posts = postService.pagingPost(page, "lo", null);

        // then
        assertNotNull(posts.getTotalElements() == 1);
    }


}