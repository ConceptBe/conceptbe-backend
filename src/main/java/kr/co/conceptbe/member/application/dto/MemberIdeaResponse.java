package kr.co.conceptbe.member.application.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaBranch;
import kr.co.conceptbe.idea.domain.IdeaTeamRecruitment;
import kr.co.conceptbe.teamrecruitment.domain.TeamRecruitment;

public record MemberIdeaResponse(
    @Schema(description = "아이디어 ID", example = "1")
    Long id,
    @Schema(description = "제목", example = "같이 프로젝트 하실분")
    String title,
    @Schema(description = "옵션(isMine, isBookmarked, isNotBookmarked", example = "isMine")
    MemberIdeaResponseOption option,
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
    @Schema(description = "분야", example = "IT, 유튜브 컨텐츠")
    @ArraySchema( arraySchema =  @Schema(
        description = "분야",
        example ="[\"IT\", \"유튜브 컨텐츠\"]"))
    List<String> branches,
    @ArraySchema( arraySchema =  @Schema(
        description = "팀원 모집 세부스킬들",
        example ="[\"영상디자인\", \"서비스기획\", \"마케팅\"]"))
    List<String> teamRecruitments
) {

    public static MemberIdeaResponse ofMember(Idea idea, MemberIdeaResponseOption option) {
        return new MemberIdeaResponse(
            idea.getId(),
            idea.getTitle(),
            option,
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
