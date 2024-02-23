package kr.co.conceptbe.idea.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.conceptbe.region.domain.Region;

public record RegionResponse(
        @Schema(description = "팀원 모집 지역 ID", example = "1")
        Long id,
        @Schema(description = "팀원 모집 지역 이름", example = "서울")
        String name
) {

    public static RegionResponse from(Region region) {
        return new RegionResponse(region.getId(), region.getName());
    }

}
