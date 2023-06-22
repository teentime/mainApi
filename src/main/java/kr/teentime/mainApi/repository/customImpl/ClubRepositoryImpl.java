package kr.teentime.mainApi.repository.customImpl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.club.FindClubDto;
import kr.teentime.mainApi.dto.club.QFindClubDto;
import kr.teentime.mainApi.repository.custom.ClubRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.teentime.mainApi.domain.QClub.club;

@Repository
@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public PagingDto<FindClubDto> findByTag(String keyword, List<String> tags, Pageable pageable) {

        List<FindClubDto> findClubDtos = query
                .select(
                        new QFindClubDto(club.name, club.intro, club.star, club.tags))
                .from(club)
                .where(club.name.contains(keyword), tagsContains(tags))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        Long count = query
                .select(club.count())
                .from(club)
                .where(club.name.contains(keyword), tagsContains(tags))
                .fetchOne();

        PagingDto<FindClubDto> page = new PagingDto<>(findClubDtos, count / pageable.getPageSize(), count);

        return page;
    }

    private BooleanExpression tagsContains(List<String> tags) {
        BooleanExpression query = null;

        for (String tag : tags) {
            if (query == null) query = club.tags.contains(tag);
            else query = query.and(club.tags.contains(tag));
        }

        return query;
    }
}
