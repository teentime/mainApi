package kr.teentime.mainApi.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import kr.teentime.mainApi.repository.PostRepository;
import kr.teentime.mainApi.testConfig.WithMockCustomUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

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
}