package kr.co.conceptbe.auth.infra.oauth.kakao.authcode;

import kr.co.conceptbe.auth.domain.authcode.AuthCodeRequestUrlProvider;
import kr.co.conceptbe.auth.infra.oauth.kakao.config.KakaoOauthConfig;
import kr.co.conceptbe.member.domain.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KakaoAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType getServerType() {
        return OauthServerType.KAKAO;
    }

    @Override
    public String getUrl() {
        return UriComponentsBuilder
            .fromUriString("https://kauth.kakao.com/oauth/authorize")
            .queryParam("response_type", "code")
            .queryParam("client_id", kakaoOauthConfig.clientId())
            .queryParam("redirect_uri", kakaoOauthConfig.redirectUri())
            .queryParam("scope", String.join(",", kakaoOauthConfig.scope()))
            .toUriString();
    }
}
