package kr.co.conceptbe.member.application.dto;

import java.util.List;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaBranch;
import kr.co.conceptbe.idea.domain.IdeaTeamRecruitment;
import kr.co.conceptbe.teamrecruitment.domain.TeamRecruitment;

public record MemberIdeaResponse(
        Long id,
        String title,
        String introduce,
        int hitsCount,
        int commentsCount,
        int likesCount,
        int bookmarksCount,
        List<String> branches,
        List<String> teamRecruitments
) {

    public static MemberIdeaResponse ofMember(Idea idea) {
        return new MemberIdeaResponse(
                idea.getId(),
                idea.getTitle(),
                idea.getIntroduce(),
                idea.getHitsCount(),
                idea.getCommentsCount(),
                idea.getLikesCount(),
                idea.getBookmarksCount(),
                getBranches(idea.getBranches()),
                getTeamRecruitments(idea.getTeamRecruitments())
        );
    }

    private static List<String> getBranches(List<IdeaBranch> branches) {
        return branches.stream()
                .map(IdeaBranch::getBranch)
                .map(Branch::getName)
                .toList();
    }

    private static List<String> getTeamRecruitments(List<IdeaTeamRecruitment> teamRecruitments) {
        return teamRecruitments.stream()
                .map(IdeaTeamRecruitment::getTeamRecruitment)
                .map(TeamRecruitment::getName)
                .toList();
    }
}
