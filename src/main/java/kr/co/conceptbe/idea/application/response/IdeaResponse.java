package kr.co.conceptbe.idea.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.idea.domain.IdeaSkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaBranch;

public record IdeaResponse(
    @Schema(description = "아이디어 ID", example = "1")
    Long id,
    @Schema(description = "제목", example = "같이 프로젝트 하실분")
    String title,
    @Schema(description = "소개", example = "같이 프로젝트 하실분을 찾습니다.")
    String introduce,
    @Schema(description = "조회수", example = "90")
    int hitsCount,
    @Schema(description = "댓글수", example = "10")
    int commentsCount,
    @Schema(description = "좋아요수", example = "5")
    int likesCount,
    @Schema(description = "북마크수", example = "3")
    int bookmarksCount,
    @Schema(description = "북마크 여부", example = "true")
    boolean isBookmarked,
    @Schema(description = "작성일", example = "2021-08-01T00:00:00")
    LocalDateTime createdAt,
    MemberResponse memberResponse,
    @Schema(description = "분야", example = "IT, 유튜브 컨텐츠")
    List<String> branches,
    @Schema(description = "팀원 모집 세부스킬들", example = "영상디자인, 서비스기획, 마케팅")
    List<String> skillCategories
) {

    public static IdeaResponse ofMember(Idea idea, boolean isBookmarked) {
        return new IdeaResponse(
            idea.getId(),
            idea.getTitle(),
            idea.getIntroduce(),
            idea.getHitsCount(),
            idea.getCommentsCount(),
            idea.getLikesCount(),
            idea.getBookmarksCount(),
            isBookmarked,
            idea.getCreatedAt(),
            MemberResponse.from(idea.getCreator()),
            getBranches(idea.getBranches()),
            getSkillCategories(idea.getIdeaSkillCategories())
        );
    }

    private static List<String> getBranches(List<IdeaBranch> branches) {
        return branches.stream()
            .map(IdeaBranch::getBranch)
            .map(Branch::getName)
            .toList();
    }

    private static List<String> getSkillCategories(List<IdeaSkillCategory> ideaSkillCategories) {
        return ideaSkillCategories.stream()
            .map(IdeaSkillCategory::getSkillCategory)
            .map(SkillCategory::getName)
            .toList();
    }

    public static IdeaResponse ofGuest(Idea idea) {
        return new IdeaResponse(
            idea.getId(),
            idea.getTitle(),
            idea.getIntroduce(),
            idea.getHitsCount(),
            idea.getCommentsCount(),
            idea.getLikesCount(),
            idea.getBookmarksCount(),
            false,
            idea.getCreatedAt(),
            MemberResponse.from(idea.getCreator()),
            getBranches(idea.getBranches()),
            getSkillCategories(idea.getIdeaSkillCategories())
        );
    }

}
