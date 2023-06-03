package kr.teentime.mainApi.repository.customImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.teentime.mainApi.dto.MemberLoginDto;
import kr.teentime.mainApi.dto.QMemberLoginDto;
import kr.teentime.mainApi.repository.custom.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static kr.teentime.mainApi.domain.QMember.*;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<MemberLoginDto> findMemberForLogin(String loginId) {

        MemberLoginDto memberDto = query.select(new QMemberLoginDto(member.password, member.phoneNumber))
                .from(member)
                .where(member.authEmail.eq(loginId).or(
                        member.phoneNumber.eq(loginId)
                )).fetchOne();

        return Optional.ofNullable(memberDto);
    }
}
