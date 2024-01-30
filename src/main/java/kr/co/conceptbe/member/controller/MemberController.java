package kr.co.conceptbe.member.controller;

import kr.co.conceptbe.member.application.MemberService;
import kr.co.conceptbe.member.application.dto.GetMemberProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/nickname/{nickname}")
    ResponseEntity<Void> checkDuplicatedNickName(
        @PathVariable String nickname
    ) {
        memberService.validateDuplicatedNickName(nickname);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<GetMemberProfileResponse> getMemberProfile(
        @PathVariable Long id
    ) {
        GetMemberProfileResponse memberProfileResponse = memberService.getMemberProfileBy(id);
        return ResponseEntity.ok(memberProfileResponse);
    }
}
