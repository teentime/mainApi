package kr.teentime.mainApi.repository;

import kr.teentime.mainApi.domain.AdminLog;
import kr.teentime.mainApi.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminLogRepository extends JpaRepository<AdminLog, Long> {

    List<AdminLog> findByClub(Club club);
}
