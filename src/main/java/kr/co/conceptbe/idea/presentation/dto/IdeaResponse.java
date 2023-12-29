package kr.co.conceptbe.idea.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;
import kr.co.conceptbe.common.entity.domain.Branch;
import kr.co.conceptbe.common.entity.domain.TeamRecruitment;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaBranch;
import kr.co.conceptbe.idea.domain.IdeaTeamRecruitment;

public record IdeaResponse(
        String title,
        String introduce,
        int hitsCount,
        int commentsCount,
        int likesCount,
        int bookmarksCount,
        boolean isBookmarked,
        LocalDateTime createdAt,
        MemberResponse memberResponse,
        List<String> branches,
        List<String> teamRecruitments
) {

    public IdeaResponse of(Idea idea, boolean isBookmarked) {
        List<String> branches = idea.getBranches()
                .stream()
                .map(IdeaBranch::getBranch)
                .map(Branch::getName)
                .toList();

        List<String> teamRecruitments = idea.getTeamRecruitments()
                .stream()
                .map(IdeaTeamRecruitment::getTeamRecruitment)
                .map(TeamRecruitment::getName)
                .toList();

        return new IdeaResponse(
                idea.getTitle(),
                idea.getIntroduce(),
                idea.getHitsCount(),
                idea.getCommentsCount(),
                idea.getLikesCount(),
                idea.getBookmarksCount(),
                isBookmarked,
                idea.getCreatedAt(),
                MemberResponse.from(idea.getCreator()),
                branches,
                teamRecruitments
        );
    }
}
