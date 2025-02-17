package kr.co.conceptbe.idea.dto;

import java.time.LocalDateTime;
import java.util.List;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.idea.application.response.BranchCategoryResponse;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaBranch;
import kr.co.conceptbe.image.application.response.ImageResponse;

public record IdeaDetailResponse(
        Long memberId,
        String imageUrl,
        String nickname,
        String mainSkill,
        String title,
        LocalDateTime date,
        String introduce,
        List<BranchCategoryResponse> branchList,
        List<String> purposeList,
        String cooperationWay,
        String recruitmentPlace,
        List<String> skillCategories,
        Integer likesCount,
        Integer commentsCount,
        Integer bookmarksCount,
        Integer hits,
        Boolean owner,
        Boolean ownerLike,
        Boolean ownerScrap,
        List<ImageResponse> imageResponses
) {

    public static IdeaDetailResponse of(
            Long tokenMemberId,
            Idea idea,
            List<ImageResponse> imageResponses
    ) {
        List<Branch> branches = idea.getBranches()
                .stream()
                .map(IdeaBranch::getBranch)
                .toList();
        List<Branch> parentBranches = branches
                .stream()
                .map(Branch::getParentBranch)
                .distinct()
                .toList();
        List<BranchCategoryResponse> branchResponses = parentBranches.stream()
                .map(parentBranch -> BranchCategoryResponse.of(parentBranch, branches))
                .toList();

        return new IdeaDetailResponse(
                idea.getCreator().getId(),
                idea.getCreator().getProfileImageUrl(),
                idea.getCreator().getNickname(),
                idea.getCreator().getMainSkill().getName(),
                idea.getTitle(),
                idea.getCreatedAt(),
                idea.getIntroduce(),
                branchResponses,
                idea.getPurposes().stream().map(e -> e.getPurpose().getName()).toList(),
                idea.getCooperationWay(),
                idea.getRecruitmentPlace(),
                idea.getSkillCategories().stream().map(e -> e.getSkillCategory().getName()).toList(),
                idea.getLikesCount(),
                idea.getComments().stream().filter(Comment::isParentComment)
                        .mapToInt(e -> e.getCommentsCount() + 1).sum(),
                idea.getBookmarksCount(),
                idea.getHitsCount(),
                idea.isOwner(tokenMemberId),
                idea.isOwnerLike(tokenMemberId),
                idea.isOwnerScrap(tokenMemberId),
                imageResponses
        );
    }

}
