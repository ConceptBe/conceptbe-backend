package kr.co.conceptbe.member.application.dto;

public record MemberProfileSkillResponse(
    Long skillId,
    String skillName,
    String level
) {
}
