package kr.teentime.mainApi.repository.custom;

import kr.teentime.mainApi.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepositoryCustom {

    Optional<Member> findMemberForLogin(String loginId);
}
