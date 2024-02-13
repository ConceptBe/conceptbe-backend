package kr.co.conceptbe.idea.dto;

import java.util.List;

import kr.co.conceptbe.member.domain.Member;

public record IdeaHitResponse (
	Long memberId,
	String nickname,
	String profileImageUrl,
	List<String> mainSkill
){
	public static IdeaHitResponse from(Member member){
		return new IdeaHitResponse(
			member.getId(),
			member.getNickname(),
			member.getProfileImageUrl(),
			member.getSkills().stream().map(skill -> skill.getSkillCategory().getName()).toList()
		);
	}
}
