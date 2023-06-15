package kr.teentime.mainApi.service;

import kr.teentime.mainApi.domain.Admin;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.dto.AddClubDto;
import kr.teentime.mainApi.repository.AdminRepository;
import kr.teentime.mainApi.repository.ClubRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

        //then
        Optional<Club> club = clubRepository.findByName(clubName);
        assertNotNull(club);

        Optional<Admin> admin = adminRepository.findByClub(club.get());
        assertNotNull(admin);
    }
}