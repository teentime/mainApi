package kr.teentime.mainApi.service;

import jakarta.persistence.EntityManager;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Review;
import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.club.AddReviewDto;
import kr.teentime.mainApi.dto.club.FindClubDto;
import kr.teentime.mainApi.dto.club.ReviewDto;
import kr.teentime.mainApi.exception.ClubNotFoundException;
import kr.teentime.mainApi.repository.ClubRepository;
import kr.teentime.mainApi.repository.ReviewRepository;
import kr.teentime.mainApi.testConfig.WithMockCustomUser;
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

@SpringBootTest
@Transactional
@Rollback
@ActiveProfiles("test")
@WithMockCustomUser
public class ReviewServiceTest {
    
    @Autowired
    private PostService postService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ClubRepository clubRepository;
    
    @Autowired
    private EntityManager em;
    
    @Test
    @DisplayName("리뷰 가져오기")
    void findReview() throws ClubNotFoundException {
        // given
        Pageable page = PageRequest.of(0, 20);

        Optional<Club> club = clubRepository.findByName("test");

        Review review = Review.builder()
                .club(club.get())
                .isAnon(true)
                .star(10)
                .content("test review")
                .build();

        reviewRepository.save(review);

        em.flush();
        em.clear();

        // when
        PagingDto<ReviewDto> reviewDto = reviewService.findReview(page, "test");
        PagingDto<FindClubDto> clubs = clubService.findClub("test", List.of(), PageRequest.of(0, 20));

        // then
        org.junit.jupiter.api.Assertions.assertNotNull(reviewDto.getItems());
        Assertions.assertThat(reviewDto.getTotalElement()).isNotEqualTo(0);
        Assertions.assertThat(reviewDto.getItems().get(0).getContent()).isEqualTo("test review");
        Assertions.assertThat(clubs.getItems().get(0).getStarAvg()).isEqualTo(10);
        Assertions.assertThat(reviewDto.getItems().get(0).getReviewedBy()).isEqualTo("익명");
    }

    @Test
    @DisplayName("리뷰 작성")
    void writeReview() throws ClubNotFoundException {
        
        // given
        Pageable page = PageRequest.of(0, 20);

        AddReviewDto review = AddReviewDto.builder()
                .star(4)
                .content("test review")
                .build();
        
        Long reviewId = reviewService.addReview(review, "test");
        
        em.flush();
        em.clear();
        
        // when
        PagingDto<ReviewDto> reviewDto = reviewRepository.findReview(page, "test");
                
        // then
        org.junit.jupiter.api.Assertions.assertNotNull(reviewDto.getItems());
        Assertions.assertThat(reviewDto.getTotalElement()).isNotEqualTo(0);
        Assertions.assertThat(reviewDto.getItems().get(0).getContent()).isEqualTo("test review");
    }
}
