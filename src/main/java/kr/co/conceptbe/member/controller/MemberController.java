package kr.co.conceptbe.member.controller;

import kr.co.conceptbe.auth.presentation.dto.TokenResponse;
import kr.co.conceptbe.member.application.MemberService;
import kr.co.conceptbe.member.application.dto.SignUpRequest;
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
    ResponseEntity<TokenResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        TokenResponse tokenResponse = memberService.signUp(signUpRequest);
        return ResponseEntity.ok(tokenResponse);
    }
}
