package kr.teentime.mainApi.service;

import kr.teentime.mainApi.domain.Admin;
import kr.teentime.mainApi.domain.AdminLog;
import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.Member;
import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.admin.AddAdminDto;
import kr.teentime.mainApi.dto.club.AddClubDto;
import kr.teentime.mainApi.dto.club.FindClubDto;
import kr.teentime.mainApi.dto.club.ReviewDto;
import kr.teentime.mainApi.exception.ClubNotFoundException;
import kr.teentime.mainApi.repository.*;
import kr.teentime.mainApi.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final AdminLogRepository log;

    public void addClub(AddClubDto addClubDto) {

        Club club = Club.builder()
                .name(addClubDto.getClubName())
                .tags(addClubDto.getTags().toString())
                .intro(addClubDto.getIntro())
                .build();

        club = clubRepository.save(club);
        Member loginMember = Util.getLoginMember();
        Optional<Member> findMember = memberRepository.findById(loginMember.getId());

        Admin admin = Admin.builder()
                .member(loginMember)
                .club(club)
                .build();
        adminRepository.save(admin);

        findMember.get().addClub(club);
    }

    public void addAdmin(AddAdminDto addAdminDto) throws IllegalAccessException, ClubNotFoundException {

        /**
         * admin으로 승격시킬 유저
         */
        Optional<Member> member = memberRepository.findByNickName(addAdminDto.getNickName());
        if (member.isEmpty()) throw new UsernameNotFoundException("member is not exist");

        Member loginMember = Util.getLoginMember();
        Optional<Club> club = clubRepository.findByName(addAdminDto.getClubName());
        if (club.isEmpty()) throw new ClubNotFoundException();

        Optional<Admin> isAdmin = adminRepository.findByClubAndMember(club.get(), loginMember);
        if (isAdmin.isEmpty()) throw new IllegalAccessException("member is not admin at this club");

        Admin admin = Admin.builder()
                .club(club.get())
                .member(member.get())
                .build();

        AdminLog adminLog = AdminLog.addLog("add admin", club.get());
        log.save(adminLog);
        adminRepository.save(admin);
    }

    public PagingDto<FindClubDto> findByTag(String keyword, List<String> tags, Pageable page) {
        PagingDto<FindClubDto> clubs = clubRepository.findByTag(keyword, tags, page);

        return clubs;
    }
}
