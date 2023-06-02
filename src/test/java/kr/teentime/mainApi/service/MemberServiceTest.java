package kr.teentime.mainApi.service;

import kr.teentime.mainApi.dto.JoinDto;
import kr.teentime.mainApi.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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

}