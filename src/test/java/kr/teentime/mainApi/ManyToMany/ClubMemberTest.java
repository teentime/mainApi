package kr.teentime.mainApi.ManyToMany;

import jakarta.persistence.EntityManager;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.ManyToMany.ClubMember;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.repository.ClubMemberRepository;
import kr.teentime.mainApi.repository.ClubRepository;
import kr.teentime.mainApi.repository.MemberRepository;
import kr.teentime.mainApi.testConfig.WithMockCustomUser;
import kr.teentime.mainApi.util.Util;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
@WithMockCustomUser
@Rollback
@ActiveProfiles("test")
public class ClubMemberTest {

    @Autowired
    ClubMemberRepository clubMemberRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    EntityManager em;

    @Test
    void testClubMember() {
        //given
        Member member = Util.getLoginMember();
        Optional<Club> club = clubRepository.findByName("test");

        ClubMember build = ClubMember.builder()
                .member(member)
                .club(club.get())
                .build();
        clubMemberRepository.save(build);

        em.flush();
        em.clear();

        //when
        Optional<Member> findMember = memberRepository.findById(member.getId());
        Optional<Club> findClub = clubRepository.findByName("test");

        //then
        Assertions.assertThat(findMember.get().getClub().size()).isEqualTo(1);
        Assertions.assertThat(findClub.get().getMembers().size()).isEqualTo(1);
    }
}
