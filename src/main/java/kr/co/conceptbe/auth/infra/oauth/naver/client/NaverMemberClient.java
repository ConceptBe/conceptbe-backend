package kr.co.conceptbe.auth.infra.oauth.naver.client;

import kr.co.conceptbe.auth.domain.client.OauthMemberClient;
import kr.co.conceptbe.auth.infra.oauth.dto.OauthMemberInformation;
import kr.co.conceptbe.auth.infra.oauth.naver.config.NaverOauthConfig;
import kr.co.conceptbe.auth.infra.oauth.naver.dto.NaverMemberResponse;
import kr.co.conceptbe.auth.infra.oauth.naver.dto.NaverMemberResponse.NaverAccount;
import kr.co.conceptbe.auth.infra.oauth.naver.dto.NaverResponseToken;
import kr.co.conceptbe.member.domain.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class NaverMemberClient implements OauthMemberClient {

    private final NaverApiClient naverApiClient;
    private final NaverOauthConfig naverOauthConfig;

    @Override
    public OauthServerType getServerType() {
        return OauthServerType.NAVER;
    }

    @Override
    public OauthMemberInformation getOauthMemberInformation(String code) {
        NaverResponseToken naverResponseToken = naverApiClient.requestToken(getTokenRequestParams(code));
        NaverMemberResponse naverMemberResponse =
            naverApiClient.requestMemberInformation("Bearer " + naverResponseToken.accessToken());
        NaverAccount naverAccount = naverMemberResponse.response();
        return new OauthMemberInformation(
            String.valueOf(naverAccount.id()),
            OauthServerType.NAVER,
            naverAccount.email(),
            naverAccount.nickname(),
            naverAccount.profileImage()
        );
    }

    private MultiValueMap<String, String> getTokenRequestParams(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", naverOauthConfig.clientId());
        params.add("redirect_uri", naverOauthConfig.redirectUri());
        params.add("code", code);
        params.add("client_secret", naverOauthConfig.clientSecret());
        params.add("state", naverOauthConfig.state());
        return params;
    }
}
