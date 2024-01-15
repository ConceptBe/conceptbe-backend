package kr.co.conceptbe.member.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.domain.OauthId;
import kr.co.conceptbe.member.domain.OauthServerType;
import kr.co.conceptbe.member.domain.Region;

public record SignUpRequest(
    @NotNull(message = "닉네임은 빈 값일 수 없습니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2자이상 10자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$", message = "닉네임은 한글/영어/숫자 중 구성되어야 하며 공백은 불가합니다.")
    String nickname,

    @NotNull(message = "대표스킬을 설정해주세요.")
    Long mainSkillId,

    @NotBlank(message = "프로필 이미지를 설정해주세요.")
    String profileImageUrl,

    @NotNull(message = "스킬은 빈 값일 수 없습니다.")
    @Size(min = 1, max = 3, message = "스킬은 1개 이상 3개이하로 선택해야 됩니다.")
    List<SignUpSkillRequest> skills,

    @NotNull(message = "가입목적은 빈 값일 수 없습니다.")
    @Size(min = 1, max = 3, message = "가입목적은 1개 이상 3개이하로 선택해야 됩니다.")
    List<Long> joinPurposes,
    String livingPlace,

    @Size(max = 10, message = "직장명은 10자 이하여야 합니다.")
    String workingPlace,

    @Size(max = 150, message = "자기소개는 150자 이하여야 합니다.")
    String introduction,

    @NotBlank(message = "이메일은 빈 값일 수 없습니다.")
    @Email(message = "올바르지 않은 이메일 형식입니다: ${validatedValue}")
    String email,

    @NotBlank(message = "oauthId는 빈 값일 수 없습니다.")
    String oauthId,

    @NotBlank(message = "oauthServerType은 빈 값일 수 없습니다.")
    String oauthServerType
) {

    public Member toMember() {
        return new Member(
            new OauthId(oauthId, OauthServerType.from(oauthServerType)),
            nickname,
            profileImageUrl,
            email,
            introduction,
            workingPlace,
            getRegion()
        );
    }

    private Region getRegion() {
        if (this.livingPlace != null) {
            return Region.from(livingPlace);
        }
        return null;
    }
}
