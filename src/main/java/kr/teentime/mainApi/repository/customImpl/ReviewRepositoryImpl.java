package kr.teentime.mainApi.repository.customImpl;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.teentime.mainApi.domain.QMember;
import kr.teentime.mainApi.domain.QReview;
import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.club.QReviewDto;
import kr.teentime.mainApi.dto.club.ReviewDto;
import kr.teentime.mainApi.repository.custom.ReviewRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.teentime.mainApi.domain.QMember.*;
import static kr.teentime.mainApi.domain.QReview.*;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public PagingDto<ReviewDto> findReview(Pageable pageable, String clubName) {
        List<ReviewDto> reviews = query.select(new QReviewDto(review.star, member.nickName, review.content, review.createdDate, review.isAnon))
                .from(review, member)
                .where(member.id.eq(review.createdBy), // 유저 기준으로 리뷰의 Id와 같은 유저의 정보를 가져옴
                        review.club.name.eq(clubName))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.id.desc())
                .fetch();

        Long count = query.select(review.count())
                .from(review)
                .where(review.club.name.eq(clubName))
                .fetchOne();

        PagingDto<ReviewDto> pagingDto = new PagingDto<>(reviews,
                (long) Math.ceil(count / pageable.getPageSize()),
                count);

        return pagingDto;
    }
}
