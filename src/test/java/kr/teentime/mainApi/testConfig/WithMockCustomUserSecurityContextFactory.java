package kr.teentime.mainApi.testConfig;

import kr.teentime.mainApi.config.security.userDetails.PrincipalDetails;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.dto.JoinDto;
import kr.teentime.mainApi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {

        final SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        Member joinDto = Member.builder()
                .authEmail("23y00000@pcs.hs.kr")
                .password("testPassword")
                .phoneNumber("01000000000")
                .nickName("테스트")
                .build();

        //when
        Member member = memberRepository.save(joinDto);

        final UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                new PrincipalDetails(member),
                "password");

        securityContext.setAuthentication(authenticationToken);
        return securityContext;
    }

}
