package kr.teentime.mainApi.repository.customImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.domain.QMember;
import kr.teentime.mainApi.repository.custom.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static kr.teentime.mainApi.domain.QMember.*;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<Member> findMemberForLogin(String loginId) {

        Member member = query.select(QMember.member)
                .from(QMember.member)
                .where(QMember.member.authEmail.eq(loginId).or(
                        QMember.member.phoneNumber.eq(loginId)
                )).fetchOne();

        return Optional.ofNullable(member);
    }
}
