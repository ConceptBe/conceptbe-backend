package kr.co.conceptbe.member.controller;

import jakarta.validation.Valid;
import java.util.List;
import kr.co.conceptbe.member.application.dto.UpdateMemberProfileRequest;
import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.common.auth.Auth;
import kr.co.conceptbe.idea.application.response.IdeaResponse;
import kr.co.conceptbe.member.application.MemberService;
import kr.co.conceptbe.member.application.dto.GetMemberProfileResponse;
import kr.co.conceptbe.member.application.dto.MemberIdeaResponse;
import kr.co.conceptbe.member.controller.doc.MemberApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController implements MemberApi {

    private final MemberService memberService;

    @GetMapping("/nickname")
    public ResponseEntity<Boolean> checkDuplicatedNickName(
        @RequestParam String nickname
    ) {
        return ResponseEntity.ok(memberService.validateDuplicatedNickName(nickname));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMemberProfileResponse> getMemberProfile(
        @Auth AuthCredentials authCredentials,
        @PathVariable Long id
    ) {
        GetMemberProfileResponse memberProfileResponse = memberService.getMemberProfileBy(authCredentials, id);
        return ResponseEntity.ok(memberProfileResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMemberProfile(
        @RequestBody @Valid UpdateMemberProfileRequest updateMemberProfileRequest,
        @Auth AuthCredentials authCredentials,
        @PathVariable Long id
    ) {
        memberService.updateMemberProfile(updateMemberProfileRequest, authCredentials, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/profile-image")
    public ResponseEntity<Void> deleteMemberProfileImage(
        @Auth AuthCredentials authCredentials,
        @PathVariable Long id
    ) {
        memberService.deleteMemberProfileImage(authCredentials, id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/ideas")
    public ResponseEntity<List<MemberIdeaResponse>> findMemberIdeas(
        @Auth AuthCredentials authCredentials,
        @PathVariable Long id,
        @RequestParam int page,
        @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<MemberIdeaResponse> memberIdeas = memberService.findMemberIdeas(authCredentials, id, pageable);

        return ResponseEntity.ok(memberIdeas);
    }

    @GetMapping("/{id}/bookmarks")
    public ResponseEntity<List<IdeaResponse>> findMemberBookmarks(
        @Auth AuthCredentials authCredentials,
        @RequestParam int page,
        @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<IdeaResponse> memberBookMarks = memberService.findMemberBookMarks(authCredentials, pageable);

        return ResponseEntity.ok(memberBookMarks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(
        @Auth AuthCredentials authCredentials,
        @PathVariable Long id
    ) {
        memberService.deleteMember(authCredentials, id);
        return ResponseEntity.noContent().build();
    }
}
