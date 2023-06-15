package kr.teentime.mainApi.service;

import kr.teentime.mainApi.domain.Admin;
import kr.teentime.mainApi.domain.AdminMember;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.domain.enums.ENUMS_clubType;
import kr.teentime.mainApi.dto.AddClubDto;
import kr.teentime.mainApi.repository.AdminMemberRepository;
import kr.teentime.mainApi.repository.AdminRepository;
import kr.teentime.mainApi.repository.ClubRepository;
import kr.teentime.mainApi.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final AdminRepository adminRepository;
    private final AdminMemberRepository adminMemberRepository;

    public void addClub(AddClubDto addClubDto) {

        Club club = Club.builder()
                .name(addClubDto.getClubName())
                .type(ENUMS_clubType.valueOf(addClubDto.getClubType()))
                .build();

        club = clubRepository.save(club);
        Member loginMember = Util.getLoginMember();

        Admin admin = Admin.builder()
                .member(loginMember)
                .club(club)
                .build();
        admin = adminRepository.save(admin);

        AdminMember adminMember = AdminMember.builder()
                .admin(admin)
                .member(loginMember)
                .build();

        adminMemberRepository.save(adminMember);

    }
}
