package kr.teentime.mainApi.repository;

import kr.teentime.mainApi.domain.ManyToMany.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {

}
