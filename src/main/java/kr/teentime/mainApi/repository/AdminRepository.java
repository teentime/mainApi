package kr.teentime.mainApi.repository;

import kr.teentime.mainApi.domain.Admin;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByClubAndMember(Club club, Member member);
    Optional<Admin> findByClub(Club club);
}
