package kr.teentime.mainApi.config.security.userDetails;

import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByPhoneNumber(username);

        if (member.isEmpty()) throw new UsernameNotFoundException("member not found");

        return new PrincipalDetails(member.get());
    }
}
