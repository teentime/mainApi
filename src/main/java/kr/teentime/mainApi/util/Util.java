package kr.teentime.mainApi.util;

import kr.teentime.mainApi.config.security.userDetails.PrincipalDetails;
import kr.teentime.mainApi.domain.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Util {

    public static Member getLoginMember() {
        try {
            PrincipalDetails member = (PrincipalDetails) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            return member.getMember();
        } catch (Exception e) {
            return null;
        }
    }
}
