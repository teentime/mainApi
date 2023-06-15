package kr.teentime.mainApi.ManyToMany;

import kr.teentime.mainApi.domain.Admin;
import kr.teentime.mainApi.domain.AdminMember;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.repository.AdminMemberRepository;
import kr.teentime.mainApi.repository.AdminRepository;
import kr.teentime.mainApi.repository.ClubRepository;
import kr.teentime.mainApi.repository.MemberRepository;
import kr.teentime.mainApi.testConfig.WithMockCustomUser;
import kr.teentime.mainApi.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback
@WithMockCustomUser
@ActiveProfiles("test")
public class AdminMemberTest {

    @Autowired
    AdminMemberRepository adminMemberRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ClubRepository clubRepository;

    @Test
    void testAdminMember() {
        //given
        Member member = Util.getLoginMember();

        Optional<Club> club = clubRepository.findByName("test");

        Admin admin = Admin.builder()
                .member(member)
                .club(club.get())
                .build();

        //when
        AdminMember adminMember = AdminMember.builder()
                .admin(admin)
                .build();
        adminMemberRepository.save(adminMember);

        //then
        Optional<Admin> findAdmin = adminRepository.findByClub(club.get());
        Assertions.assertNotNull(findAdmin);
    }
}
