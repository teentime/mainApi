package kr.teentime.mainApi.service;

import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.dto.JoinDto;
import kr.teentime.mainApi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(JoinDto joinDto) {
        String encodedPassword = passwordEncoder.encode(joinDto.getPassword());

        Member member = Member.builder()
                .authEmail(joinDto.getAuthEmail())
                .email(joinDto.getEmail())
                .nickName(joinDto.getNickname())
                .phoneNumber(joinDto.getPhoneNumber())
                .password(encodedPassword)
                .build();

        memberRepository.save(member);
    }
}
