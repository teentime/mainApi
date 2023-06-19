package kr.teentime.mainApi.repository;

import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.repository.custom.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByNickName(String nickname);
    Optional<Member> findByPhoneNumber(String phoneNumber);
}

