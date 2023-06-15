package kr.teentime.mainApi.service;

import kr.teentime.mainApi.dto.member.JoinDto;
import kr.teentime.mainApi.dto.member.LoginDto;
import kr.teentime.mainApi.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Rollback
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        // given
        JoinDto joinDto = JoinDto.builder()
                .authEmail("23y00000@pcs.hs.kr")
                .password("testPassword")
                .phoneNumber("01000000000")
                .schoolId(1L)
                .nickname("테스트")
                .build();

        //when
        memberService.join(joinDto);

        //then
        assertNotNull(memberRepository.findByPhoneNumber("01000000000"));
    }

    @Test
    void login() {
        // given
        JoinDto joinDto = JoinDto.builder()
                .authEmail("23y00000@pcs.hs.kr")
                .password("testPassword")
                .phoneNumber("01000000000")
                .schoolId(1L)
                .nickname("테스트")
                .build();

        memberService.join(joinDto);

        LoginDto loginInfo = LoginDto.builder()
                .loginId("01000000000")
                .password("testPassword")
                .build();

        // when
        Map<String, String> token = memberService.login(loginInfo);

        // then
        assertNotNull(token.get("accessToken"));
        assertNotNull(token.get("refreshToken"));
    }
}