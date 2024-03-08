package kr.co.conceptbe.idea.application.request;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record IdeaUpdateRequest(
    @Schema(description = "제목", example = "같이 프로젝트 하실분")
    String title,
    @Schema(description = "소개", example = "같이 프로젝트 하실분을 찾습니다.")
    String introduce,
    @Schema(description = "협업 방식", example = "온라인")
    String cooperationWay,
    @Schema(description = "모집 지역 ID", example = "1")
    Long recruitmentPlaceId,
    @ArraySchema(arraySchema = @Schema(description = "분야 ID 목록", example = "[\"1\", \"2\"]"))
    List<Long> branchIds,
    @ArraySchema(arraySchema = @Schema(description = "목적 ID 목록", example = "[\"1\", \"2\"]"))
    List<Long> purposeIds,
    @ArraySchema(arraySchema = @Schema(description = "팀원 ID 목록", example = "[\"1\", \"2\", \"3\", \"4\"]"))
    List<Long> skillCategoryIds
) {

}
