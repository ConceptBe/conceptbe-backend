package kr.co.conceptbe.auth.infra.oauth.naver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.naver")
public record NaverOauthConfig(
    String clientId,
    String redirectUri,
    String[] scope,
    String clientSecret,
    String state
) {
}
