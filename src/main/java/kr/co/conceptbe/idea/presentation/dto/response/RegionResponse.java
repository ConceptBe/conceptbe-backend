package kr.co.conceptbe.idea.presentation.dto.response;

import kr.co.conceptbe.region.domain.Region;

public record RegionResponse(
        Long id,
        String name
) {

    public static RegionResponse from(Region region) {
        return new RegionResponse(region.getId(), region.getName());
    }

}
