package kr.co.conceptbe.member.controller;

import java.util.List;
import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.common.auth.Auth;
import kr.co.conceptbe.member.application.MemberService;
import kr.co.conceptbe.member.application.dto.GetMemberProfileResponse;
import kr.co.conceptbe.member.application.dto.MemberIdeaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{id}/ideas")
    public ResponseEntity<List<MemberIdeaResponse>> findMemberIdeas(
        @Auth AuthCredentials authCredentials,
        @RequestParam int page,
        @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<MemberIdeaResponse> responses = memberService.findMemberIdeas(authCredentials, pageable);

        return ResponseEntity.ok(responses);
    }
}
