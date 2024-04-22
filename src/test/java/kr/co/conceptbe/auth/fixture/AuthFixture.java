package kr.co.conceptbe.auth.fixture;

import java.util.List;
import kr.co.conceptbe.auth.application.dto.SignUpRequest;
import kr.co.conceptbe.auth.application.dto.SkillRequest;
import kr.co.conceptbe.member.domain.OauthServerType;
import kr.co.conceptbe.region.domain.Region;

public class AuthFixture {

    public static SignUpRequest createSignUpRequest(
        Long mainSkillId,
        List<SkillRequest> skills,
        Long purposeId,
        Region region
    ) {
        return new SignUpRequest("nickname",
            mainSkillId,
            "image_url",
            skills,
            List.of(purposeId),
            region.getId(),
            null,
            "안녕하세요",
            "email",
            "oauthId",
            OauthServerType.KAKAO.name()
        );
    }
}
