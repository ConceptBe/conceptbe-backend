package kr.co.conceptbe.auth.infra.oauth.kakao.client;

import kr.co.conceptbe.auth.infra.oauth.kakao.dto.KakaoMemberResponse;
import kr.co.conceptbe.auth.infra.oauth.kakao.dto.KakaoMemberResponse.KakaoAccount;
import kr.co.conceptbe.auth.infra.oauth.kakao.dto.KakaoMemberResponse.Profile;
import kr.co.conceptbe.auth.infra.oauth.kakao.dto.KakaoResponseToken;
import kr.co.conceptbe.auth.infra.oauth.dto.OauthMemberInformation;
import kr.co.conceptbe.auth.domain.client.OauthMemberClient;
import kr.co.conceptbe.auth.infra.oauth.kakao.config.KakaoOauthConfig;
import kr.co.conceptbe.member.domain.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType getServerType() {
        return OauthServerType.KAKAO;
    }

    @Override
    public OauthMemberInformation getOauthMemberInformation(String code) {
        KakaoResponseToken kakaoResponseToken = kakaoApiClient.requestToken(getTokenRequestParams(code));
        KakaoMemberResponse kakaoMemberResponse =
            kakaoApiClient.requestMemberInformation("Bearer " + kakaoResponseToken.accessToken());
        KakaoAccount kakaoAccount = kakaoMemberResponse.kakaoAccount();
        Profile profile = kakaoAccount.profile();
        return new OauthMemberInformation(
            String.valueOf(kakaoMemberResponse.id()),
            OauthServerType.KAKAO,
            kakaoAccount.email(),
            profile.nickname(),
            profile.profileImageUrl()
        );
    }

    private MultiValueMap<String, String> getTokenRequestParams(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.clientId());
        params.add("redirect_uri", kakaoOauthConfig.redirectUri());
        params.add("code", code);
        params.add("client_secret", kakaoOauthConfig.clientSecret());
        return params;
    }
}
