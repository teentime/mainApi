package kr.teentime.mainApi.repository;

import kr.teentime.mainApi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

}
