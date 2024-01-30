package kr.co.conceptbe.member.application.dto;

import java.util.List;

public record GetMemberProfileResponse(
    String profileImageUrl,
    String nickname,
    String mainSkill,
    String livingPlace,
    String workingPlace,
    String introduction,
    List<String> skills,
    List<String> joinPurposes
) {
}
