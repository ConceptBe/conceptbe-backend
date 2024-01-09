package kr.co.conceptbe.member.controller;

import jakarta.validation.Valid;
import kr.co.conceptbe.auth.presentation.dto.TokenResponse;
import kr.co.conceptbe.member.application.MemberService;
import kr.co.conceptbe.member.application.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    ResponseEntity<TokenResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        TokenResponse tokenResponse = memberService.signUp(signUpRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/members/nickname/{nickname}")
    ResponseEntity<Void> checkDuplicatedNickName(
        @PathVariable String nickname
    ) {
        memberService.validateDuplicatedNickName(nickname);
        return ResponseEntity.ok().build();
    }
}
