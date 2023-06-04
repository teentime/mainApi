package kr.teentime.mainApi.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import kr.teentime.mainApi.domain.Post;
import kr.teentime.mainApi.dto.dslDto.PostPagingDto;
import kr.teentime.mainApi.repository.PostRepository;
import kr.teentime.mainApi.testConfig.WithMockCustomUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback
@ActiveProfiles("test")
@WithMockCustomUser
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    EntityManager em;

    @Test
    void createPost() {
        // given
        String title = "test";
        String content = "content";

        // when
        postService.writePost(title, content);
        
        em.flush();
        em.clear();

        // then
        assertNotNull(postRepository.findAll());
    }

    @Test
    void pagingPost() {
        // given
        String title = "test";
        String content = "content";
        postService.writePost(title, content);

        Pageable page = PageRequest.of(0, 20);

        // when

        Page posts = postService.pagingPost(page, "", null);

        // then
        assertNotNull(posts);
    }
}