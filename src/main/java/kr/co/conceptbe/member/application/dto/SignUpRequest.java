package kr.co.conceptbe.member.application.dto;

import java.util.List;
import kr.co.conceptbe.member.Member;
import kr.co.conceptbe.member.OauthId;
import kr.co.conceptbe.member.OauthServerType;

public record SignUpRequest(
    String nickname,
    Long mainSkillId,
    String profileImageUrl,
    List<SignUpSkillRequest> skills,
    List<Long> joinPurposes,
    Long livingPlace,
    String workingPlace,
    String introduction,
    String email,
    String oauthId,
    String oauthServerType
) {

    public Member toMember() {
        return new Member(
            new OauthId(oauthId, OauthServerType.from(oauthServerType)),
            nickname,
            profileImageUrl,
            email,
            introduction,
            workingPlace
        );
    }
}
