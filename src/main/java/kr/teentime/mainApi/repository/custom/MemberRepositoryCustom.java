package kr.teentime.mainApi.repository.custom;

import kr.teentime.mainApi.dto.dslDto.MemberLoginDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepositoryCustom {

    Optional<MemberLoginDto> findMemberForLogin(String loginId);
}
