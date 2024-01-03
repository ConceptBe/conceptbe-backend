package kr.co.conceptbe.member.fixture;

import java.util.List;
import kr.co.conceptbe.member.OauthServerType;
import kr.co.conceptbe.member.Region;
import kr.co.conceptbe.member.application.dto.SignUpRequest;
import kr.co.conceptbe.member.application.dto.SignUpSkillRequest;

public class MemberFixture {

    public static SignUpRequest createSignUpRequest(
        Long mainSkillId,
        List<SignUpSkillRequest> skills,
        Long purposeId
    ) {
        return new SignUpRequest("nickname",
            mainSkillId,
            "image_url",
            skills,
            List.of(purposeId),
            Region.SEOUL.getName(),
            null,
            "안녕하세요",
            "email",
            "oauthId",
            OauthServerType.KAKAO.name()
        );
    }
}
