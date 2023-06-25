package kr.teentime.mainApi.service;

import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Review;
import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.club.AddReviewDto;
import kr.teentime.mainApi.dto.club.ReviewDto;
import kr.teentime.mainApi.exception.ClubNotFoundException;
import kr.teentime.mainApi.repository.ClubRepository;
import kr.teentime.mainApi.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ClubRepository clubRepository;

    public Long addReview(AddReviewDto addReviewDto, String clubName) throws ClubNotFoundException {
        Optional<Club> club = clubRepository.findByName(clubName);
        if (club.isEmpty()) throw new ClubNotFoundException();

        Review review = Review.builder()
                .star(addReviewDto.getStar())
                .isAnon(addReviewDto.isAnon())
                .content(addReviewDto.getContent())
                .club(club.get())
                .build();

        Long reviewId = reviewRepository.save(review).getId();

        return reviewId;
    }

    public PagingDto<ReviewDto> findReview(Pageable pageable, String clubName) throws ClubNotFoundException {
        Optional<Club> club = clubRepository.findByName(clubName);
        if (club.isEmpty()) throw new ClubNotFoundException();

        PagingDto<ReviewDto> review = reviewRepository.findReview(pageable, clubName);

        // 익명으로 선택된 리뷰의 작성자 이름을 "익명"으로 변경
        List<ReviewDto> convertWriterName = review.getItems().stream().map(e -> {
            if (e.isAnon()) e.setReviewedBy("익명");
            return e;
        }).toList();

        review.setItems(convertWriterName);

        return review;
    }
}
