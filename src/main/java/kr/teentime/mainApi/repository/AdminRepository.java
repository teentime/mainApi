package kr.teentime.mainApi.repository;

import kr.teentime.mainApi.domain.Admin;
import kr.teentime.mainApi.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByClub(Club club);
}
