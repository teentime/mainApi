package kr.teentime.mainApi.repository;

import kr.teentime.mainApi.domain.Admin;
import kr.teentime.mainApi.domain.ManyToMany.AdminMember;
import kr.teentime.mainApi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminMemberRepository extends JpaRepository<AdminMember, Long> {

    Optional<AdminMember> findByMember(Member member);
    Optional<AdminMember> findByAdmin(Admin admin);
}
