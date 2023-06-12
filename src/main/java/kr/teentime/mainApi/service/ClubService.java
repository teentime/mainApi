package kr.teentime.mainApi.service;

import kr.teentime.mainApi.domain.Club;
import kr.teentime.mainApi.domain.enums.ENUMS_clubType;
import kr.teentime.mainApi.dto.AddClubDto;
import kr.teentime.mainApi.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public void addClub(AddClubDto addClubDto) {

        Club club = Club.builder()
                .name(addClubDto.getClubName())
                .type(ENUMS_clubType.valueOf(addClubDto.getClubType()))
                .build();

        clubRepository.save(club);
    }
}
