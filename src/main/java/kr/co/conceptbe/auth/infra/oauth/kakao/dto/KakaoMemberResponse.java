package kr.co.conceptbe.auth.infra.oauth.kakao.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record KakaoMemberResponse(
        Long id,
        KakaoAccount kakaoAccount
) {

    @JsonNaming(SnakeCaseStrategy.class)
    public record KakaoAccount(
            Profile profile,
            String email
    ) {
    }

    @JsonNaming(SnakeCaseStrategy.class)
    public record Profile(
            String nickname,
            String profileImageUrl
    ) {
    }
}
