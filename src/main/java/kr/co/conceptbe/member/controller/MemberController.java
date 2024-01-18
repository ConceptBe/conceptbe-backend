package kr.co.conceptbe.member.controller;

import kr.co.conceptbe.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/nickname/{nickname}")
    ResponseEntity<Void> checkDuplicatedNickName(
        @PathVariable String nickname
    ) {
        memberService.validateDuplicatedNickName(nickname);
        return ResponseEntity.ok().build();
    }
}
