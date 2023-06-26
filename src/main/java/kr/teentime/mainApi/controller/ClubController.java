package kr.teentime.mainApi.controller;

import jakarta.validation.Valid;
import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.admin.AddAdminDto;
import kr.teentime.mainApi.dto.club.AddClubDto;
import kr.teentime.mainApi.dto.club.FindClubDto;
import kr.teentime.mainApi.exception.ClubNotFoundException;
import kr.teentime.mainApi.service.ClubService;
import kr.teentime.mainApi.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @PostMapping("/club/add")
    public ResponseEntity addClub(@RequestBody @Valid AddClubDto addClubDto) {

        clubService.addClub(addClubDto);
        return Result.ok("add club is successfully done");
    }

    @PostMapping("/club/admin/add")
    public ResponseEntity addAdmin(@RequestBody @Valid AddAdminDto addAdminDto) throws ClubNotFoundException {

        try {
            clubService.addAdmin(addAdminDto);
            return Result.ok("add club admin is successfully done");
        } catch (IllegalAccessException e) {
            return Result.error(e.getMessage(), HttpStatus.FORBIDDEN.value());
        }
    }

    @GetMapping("/clubs")
    public ResponseEntity searchClubs(@RequestParam("query") String keyword,
                                      @RequestParam("tags") List<String> tags,
                                      Pageable pageable) {
        PagingDto<FindClubDto> clubs = clubService.findClub(keyword, tags, pageable);

        return Result.ok(clubs);
    }

}
