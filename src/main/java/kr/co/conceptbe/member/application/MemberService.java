package kr.co.conceptbe.member.application;

import kr.co.conceptbe.auth.presentation.dto.TokenResponse;
import kr.co.conceptbe.auth.support.JwtProvider;
import kr.co.conceptbe.member.Member;
import kr.co.conceptbe.member.MemberRepository;
import kr.co.conceptbe.member.application.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public TokenResponse signUp(SignUpRequest signUpRequest) {
        Member member = signUpRequest.toMember();
        memberRepository.save(member);
        String accessToken = jwtProvider.createAccessToken(member.getId());
        //TODO 대표스킬, 스킬들, 목적 저장 추가
        return TokenResponse.from(accessToken);
    }
}
