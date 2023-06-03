package kr.teentime.mainApi.service;

import kr.teentime.mainApi.config.security.jwt.JwtConfig;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.dto.JoinDto;
import kr.teentime.mainApi.dto.LoginDto;
import kr.teentime.mainApi.dto.MemberLoginDto;
import kr.teentime.mainApi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

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

    /**
     * @return accessToken, refreshToken
     * @param loginDto
     */
    public Map<String, String> login(LoginDto loginDto) {
        Optional<MemberLoginDto> member = memberRepository.findMemberForLogin(loginDto.getLoginId());

        if (member.isEmpty() || !passwordEncoder.matches(loginDto.getPassword(), member.get().getPassword()))
            throw new UsernameNotFoundException("member not found");

        String accessToken = jwtConfig.createToken(member.get().getPhoneNumber());
        String refreshToken = jwtConfig.createRefreshToken(member.get().getPhoneNumber());

        return Map.of("accessToken", accessToken,
                "refreshToken", refreshToken);
    }
}
