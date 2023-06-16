package kr.teentime.mainApi.service;

import jakarta.persistence.EntityManager;
import kr.teentime.mainApi.domain.Admin;
import kr.teentime.mainApi.domain.AdminLog;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.dto.admin.AddAdminDto;
import kr.teentime.mainApi.dto.club.AddClubDto;
import kr.teentime.mainApi.exception.ClubNotFoundException;
import kr.teentime.mainApi.repository.AdminLogRepository;
import kr.teentime.mainApi.repository.AdminRepository;
import kr.teentime.mainApi.repository.ClubRepository;
import kr.teentime.mainApi.repository.MemberRepository;
import kr.teentime.mainApi.testConfig.WithMockCustomUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Rollback
@ActiveProfiles("test")
class ClubServiceTest {

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminLogRepository adminLogRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;


    @Test
    @DisplayName("동아리 추가")
    void addClub() {
        // given
        String clubName = "testClub";
        AddClubDto addClubDto = AddClubDto.builder()
                .clubName(clubName)
                .clubType("CLUB")
                .build();

        //when
        assertDoesNotThrow(() -> clubService.addClub(addClubDto));
        em.flush();
        em.clear();

        //then
        Optional<Club> club = clubRepository.findByName(clubName);
        assertNotNull(club);

        Optional<Admin> admin = adminRepository.findByClub(club.get());
        assertNotNull(admin);
    }

    @Test
    @DisplayName("관리자 추가")
    @WithMockCustomUser
    void addAdmin() throws IllegalAccessException, ClubNotFoundException {
        // given
        Member member = Member.builder()
                .email("test@testEmail.com")
                .phoneNumber("01000000011")
                .nickName("addAdminTest")
                .password("testtesttest")
                .authEmail("test@Admin.com")
                .build();
        member = memberRepository.save(member);

        String clubName = "addAdminTestClub";
        AddClubDto addClubDto = AddClubDto.builder()
                .clubName(clubName)
                .clubType("CLUB")
                .build();
        clubService.addClub(addClubDto);

        AddAdminDto addAdminDto = AddAdminDto.builder()
                .memberEmail(member.getEmail())
                .clubName(clubName)
                .build();

        // when
        clubService.addAdmin(addAdminDto);
        em.flush();
        em.clear();

        // then
        Optional<Club> findClub = clubRepository.findByName(clubName);
        assertNotNull(adminRepository.findByClubAndMember(findClub.get(), member));

        List<AdminLog> logs = adminLogRepository.findByClub(findClub.get());
        assertNotNull(logs);
    }
}