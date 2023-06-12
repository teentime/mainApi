package kr.teentime.mainApi.repository.customImpl;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.teentime.mainApi.domain.QClub;
import kr.teentime.mainApi.dto.dslDto.PostPagingDto;
import kr.teentime.mainApi.dto.dslDto.QPostPagingDto;
import kr.teentime.mainApi.repository.custom.PostRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static kr.teentime.mainApi.domain.QClub.club;
import static kr.teentime.mainApi.domain.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Page pagingPost(Pageable page, String keyword, String clubName) {
        JPAQuery<PostPagingDto> statement = query.select(
                        new QPostPagingDto(
                                post.title, post.content, post.view, post.createdDate,
                                new CaseBuilder()
                                        .when(post.isAnon)
                                        .then("익명")
                                        .otherwise(post.member.nickName)
                        ))
                .from(post)
                .join(post.club, club)
                .where(post.content.containsIgnoreCase(keyword)
                        .and(club.name.eq(clubName)));

        List<PostPagingDto> postList = statement
                .orderBy(post.id.desc())
                .offset(page.getOffset())
                .limit(page.getPageSize())
                .fetch();

        JPAQuery<Long> totalCount = query.select(post.count())
                .from(post)
                .where(post.content.contains(keyword));

        return PageableExecutionUtils.getPage(postList, page, totalCount::fetchOne);
    }
}
