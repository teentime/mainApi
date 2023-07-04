package kr.teentime.mainApi.repository.customImpl;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.post.PostPagingDto;
import kr.teentime.mainApi.dto.post.QPostPagingDto;
import kr.teentime.mainApi.repository.custom.PostRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static kr.teentime.mainApi.domain.QClub.club;
import static kr.teentime.mainApi.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public PagingDto<PostPagingDto> pagingPost(Pageable page, String keyword, String clubName) {
        JPAQuery<PostPagingDto> statement = query.select(
                        new QPostPagingDto(
                                post.title, post.content, post.view, post.createdDate,
                                new CaseBuilder()
                                        .when(post.isAnon)
                                        .then("익명")
                                        .otherwise(post.member.nickName),
                                returnClubName(clubName)
                        ))
                .from(post);

        // join을 할지 말지
        statement = joinClub(statement, clubName)
                .where(post.content.containsIgnoreCase(keyword));

        List<PostPagingDto> postList = statement
                .orderBy(post.id.desc())
                .offset(page.getOffset())
                .limit(page.getPageSize())
                .fetch();

        Long totalCnt = query.select(post.count())
                .from(post)
                .where(post.content.contains(keyword))
                .fetchOne();

        return new PagingDto<PostPagingDto>(postList, totalCnt / page.getPageSize(), totalCnt);
    }

    private BooleanExpression clubNameValid(String clubName) {
        return clubName == null || clubName.isEmpty() ? null : club.name.eq(clubName);
    }

    private Expression<String> returnClubName(String clubName) {
        return clubName == null || clubName.isEmpty() ? Expressions.asString("") : club.name;
    }

    private JPAQuery<PostPagingDto> joinClub(JPAQuery<PostPagingDto> query, String clubName) {
        if (clubName == null || clubName.isEmpty()) {
            return query.leftJoin(post.club, club);
        }

        return query;
    }
}
