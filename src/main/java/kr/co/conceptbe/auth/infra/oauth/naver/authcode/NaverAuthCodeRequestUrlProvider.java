package kr.co.conceptbe.auth.infra.oauth.naver.authcode;

import kr.co.conceptbe.auth.domain.authcode.AuthCodeRequestUrlProvider;
import kr.co.conceptbe.auth.infra.oauth.naver.config.NaverOauthConfig;
import kr.co.conceptbe.member.domain.OauthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class NaverAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final NaverOauthConfig naverOauthConfig;

    @Override
    public OauthServerType getServerType() {
        return OauthServerType.NAVER;
    }

    @Override
    public String getUrl() {
        return UriComponentsBuilder
            .fromUriString("https://nid.naver.com/oauth2.0/authorize")
            .queryParam("response_type", "code")
            .queryParam("client_id", naverOauthConfig.clientId())
            .queryParam("redirect_uri", naverOauthConfig.redirectUri())
            .queryParam("scope", String.join(",", naverOauthConfig.scope()))
            .queryParam("state", "RANDOM_STATE")
            .toUriString();
    }
}
