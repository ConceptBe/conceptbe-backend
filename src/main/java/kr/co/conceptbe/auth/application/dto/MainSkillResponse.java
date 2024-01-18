package kr.co.conceptbe.auth.application.dto;

import java.util.List;

public record MainSkillResponse(
    Long id,
    String name,
    List<DetailSkillResponse> detailSkillResponses
) {
}
