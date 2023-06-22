package kr.teentime.mainApi.repository;

import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.repository.custom.ClubRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long>, ClubRepositoryCustom {

    Optional<Club> findByName(String name);
}
