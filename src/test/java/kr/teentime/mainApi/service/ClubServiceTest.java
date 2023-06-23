package kr.teentime.mainApi.service;

import jakarta.persistence.EntityManager;
import kr.teentime.mainApi.domain.Admin;
import kr.teentime.mainApi.domain.AdminLog;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.admin.AddAdminDto;
import kr.teentime.mainApi.dto.club.AddClubDto;
import kr.teentime.mainApi.dto.club.FindClubDto;
import kr.teentime.mainApi.exception.ClubNotFoundException;
import kr.teentime.mainApi.repository.AdminLogRepository;
import kr.teentime.mainApi.repository.AdminRepository;
import kr.teentime.mainApi.repository.ClubRepository;
import kr.teentime.mainApi.repository.MemberRepository;
import kr.teentime.mainApi.testConfig.WithMockCustomUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
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
@WithMockCustomUser
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
        List<String> category = List.of("java", "프로그래밍");
        String intro = "this club is just test club";
        AddClubDto addClubDto = AddClubDto.builder()
                .clubName(clubName)
                .intro(intro)
                .tags(category)
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
                .intro("introduction")
                .tags(List.of("test"))
                .build();
        clubService.addClub(addClubDto);

        AddAdminDto addAdminDto = AddAdminDto.builder()
                .nickName(member.getNickName())
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

    @Test
    @DisplayName("동아리 검색 - 태그 활용")
    void searchClubTag() {
        // given
        String clubName = "searchClubTest";
        AddClubDto addClubDto = AddClubDto.builder()
                .clubName(clubName)
                .intro("introduction for search")
                .tags(List.of("test", "search"))
                .build();
        clubService.addClub(addClubDto);

        String clubName2 = "searchClubTest2";
        AddClubDto addClubDto2 = AddClubDto.builder()
                .clubName(clubName2)
                .intro("introduction for search2")
                .tags(List.of("test"))
                .build();
        clubService.addClub(addClubDto2);

        Pageable page = Pageable.ofSize(1);

        // when
        PagingDto<FindClubDto> clubs = clubService.findByTag("", List.of("test"), page);
        PagingDto<FindClubDto> clubBySearchTag = clubService.findByTag("", List.of("test", "search"), page);

        // then
        Assertions.assertThat(clubs.getTotalElement()).isEqualTo(2);
        Assertions.assertThat(clubBySearchTag.getTotalElement()).isEqualTo(1);

        Assertions.assertThat(clubBySearchTag.getItems().get(0).getName()).isEqualTo(clubName);
        Assertions.assertThat(clubBySearchTag.getItems().get(0).getTags()).isEqualTo(List.of("test", "search"));

    }
}