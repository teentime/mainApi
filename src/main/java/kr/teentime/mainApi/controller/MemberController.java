package kr.teentime.mainApi.controller;

import jakarta.validation.Valid;
import kr.teentime.mainApi.dto.JoinDto;
import kr.teentime.mainApi.dto.LoginDto;
import kr.teentime.mainApi.service.MemberService;
import kr.teentime.mainApi.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto) {
        try {
            Map<String, String> token = memberService.login(loginDto);

            HttpHeaders header = new HttpHeaders();
            header.add("X-Auth-Token", "Bearer " + token.get("accessToken"));
            header.add("X-Refresh-Token", "Bearer " + token.get("refreshToken"));

            return ResponseEntity.ok()
                    .headers(header)
                    .body(new Result<>(null, false));

        } catch (UsernameNotFoundException e) {
            return Result.error("id or password is not exist",
                    HttpStatus.NOT_FOUND.value());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Valid @RequestBody JoinDto joinDto) {

        try {
            memberService.join(joinDto);

            return Result.ok(null);

        } catch (DataIntegrityViolationException e) {
            return Result.error("id or password is already exist", HttpStatus.CONFLICT.value());
        }
    }
}
