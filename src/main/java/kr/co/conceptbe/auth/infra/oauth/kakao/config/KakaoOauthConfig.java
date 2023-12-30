package kr.co.conceptbe.auth.infra.oauth.kakao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.kakao")
public record KakaoOauthConfig(
    String clientId,
    String redirectUri,
    String[] scope,
    String clientSecret
) {
}
