package kr.teentime.mainApi.repository;

import kr.teentime.mainApi.domain.Review;
import kr.teentime.mainApi.repository.custom.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}
