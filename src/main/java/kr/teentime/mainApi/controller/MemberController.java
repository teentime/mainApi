package kr.teentime.mainApi.controller;

import jakarta.validation.Valid;
import kr.teentime.mainApi.dto.JoinDto;
import kr.teentime.mainApi.service.MemberService;
import kr.teentime.mainApi.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Valid @RequestBody JoinDto joinDto) {

        try {
            memberService.join(joinDto);

            return Result.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.internalError();
        }
    }
}
