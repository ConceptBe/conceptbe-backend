package kr.co.conceptbe.member.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import kr.co.conceptbe.auth.application.dto.SkillRequest;

public record UpdateMemberProfileRequest(
    @Schema(description = "닉네임", example = "닉네임")
    @NotNull(message = "닉네임은 빈 값일 수 없습니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2자이상 10자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$", message = "닉네임은 한글/영어/숫자 중 구성되어야 합니다.")
    String nickname,

    @Schema(description = "대표스킬 ID", example = "1")
    @NotNull(message = "대표스킬을 설정해주세요.")
    Long mainSkillId,

    @Schema(description = "프로필 이미지 URL", example = "https://conceptbe.png")
    @NotBlank(message = "프로필 이미지를 설정해주세요.")
    String profileImageUrl,

    @Schema(description = "세부 스킬 리스트")
    @NotNull(message = "스킬은 빈 값일 수 없습니다.")
    @Size(min = 1, max = 3, message = "스킬은 1개 이상 3개이하로 선택해야 됩니다.")
    List<SkillRequest> skills,

    @Schema(description = "가입 목적 리스트")
    @NotNull(message = "가입목적은 빈 값일 수 없습니다.")
    @Size(min = 1, max = 3, message = "가입목적은 1개 이상 3개이하로 선택해야 됩니다.")
    List<Long> joinPurposes,

    @Schema(description = "지역 ID", example = "1")
    @NotNull(message = "지역을 선택해주세요.")
    Long livingPlaceId,

    @Schema(description = "직장명", example = "토스")
    @Size(max = 10, message = "직장명은 10자 이하여야 합니다.")
    String workingPlace,

    @Schema(description = "자기소개", example = "안녕하세요")
    @Size(max = 150, message = "자기소개는 150자 이하여야 합니다.")
    String introduction
) {

}
