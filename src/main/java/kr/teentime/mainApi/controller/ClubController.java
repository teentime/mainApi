package kr.teentime.mainApi.controller;

import jakarta.validation.Valid;
import kr.teentime.mainApi.dto.club.AddClubDto;
import kr.teentime.mainApi.service.ClubService;
import kr.teentime.mainApi.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @PostMapping("/club/add")
    public ResponseEntity addClub(@RequestBody @Valid AddClubDto addClubDto) {

        clubService.addClub(addClubDto);
        return Result.ok("add club is successfully");
    }

}
