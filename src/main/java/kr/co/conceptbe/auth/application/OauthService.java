package kr.co.conceptbe.auth.application;

import kr.co.conceptbe.auth.application.dto.OauthMemberResponse;
import kr.co.conceptbe.auth.domain.client.OauthMemberClientHandler;
import kr.co.conceptbe.auth.infra.oauth.dto.OauthMemberInformation;
import kr.co.conceptbe.auth.domain.authcode.AuthCodeRequestUrlProviderHandler;
import kr.co.conceptbe.auth.support.JwtProvider;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.persistence.MemberRepository;
import kr.co.conceptbe.member.domain.OauthId;
import kr.co.conceptbe.member.domain.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final MemberRepository memberRepository;
    private final OauthMemberClientHandler oauthMemberClientHandler;
    private final AuthCodeRequestUrlProviderHandler authCodeRequestUrlProviderHandler;
    private final JwtProvider jwtProvider;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderHandler.provideUrl(oauthServerType);
    }

    //TODO 외부와 Transactional 분리 예정
    public OauthMemberResponse getOauthMemberInformationBy(OauthServerType oauthServerType, String code) {
        OauthMemberInformation oauthMemberInformation = oauthMemberClientHandler.getOauthMemberInformation(oauthServerType, code);
        boolean isMember = memberRepository.existsByOauthId(
            new OauthId(String.valueOf(oauthMemberInformation.oauthId()), oauthServerType)
        );
        return new OauthMemberResponse(isMember, oauthMemberInformation);
    }

    public String login(String oauthId, OauthServerType oauthServerType) {
        Member member = memberRepository.getByOauthId(new OauthId(oauthId, oauthServerType));
        return jwtProvider.createAccessToken(member.getId());
    }
}
