package kr.teentime.mainApi.config;

import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.util.Util;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AuditConfig implements AuditorAware<Long> {

    @NonNull
    @Override
    public Optional<Long> getCurrentAuditor() {
        Member loginMember = Util.getLoginMember();

        if (loginMember == null) return Optional.empty();

        return Optional.ofNullable(loginMember.getId());
    }
}
