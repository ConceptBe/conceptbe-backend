package kr.co.conceptbe.auth.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import kr.co.conceptbe.auth.application.dto.AuthResponse;
import kr.co.conceptbe.auth.application.dto.FindSignUpResponse;
import kr.co.conceptbe.auth.application.dto.SignUpRequest;
import kr.co.conceptbe.auth.application.dto.SkillRequest;
import kr.co.conceptbe.auth.fixture.AuthFixture;
import kr.co.conceptbe.auth.support.JwtProvider;
import kr.co.conceptbe.member.application.MemberService;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.persistence.MemberRepository;
import kr.co.conceptbe.member.persistence.MemberSkillRepository;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.purpose.domain.persistence.PurposeRepository;
import kr.co.conceptbe.region.domain.Region;
import kr.co.conceptbe.region.domain.presentation.RegionRepository;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
import kr.co.conceptbe.skill.domain.SkillLevel;
import kr.co.conceptbe.skill.exception.DuplicatedSkillCategoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class OauthServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberSkillRepository memberSkillRepository;



    @Autowired
    private OauthService oauthService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Autowired
    private PurposeRepository purposeRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private JwtProvider jwtProvider;


    @Test
    void 회원가입을_할_수_있다() {
        //given
        SkillCategory mainSkill = skillCategoryRepository.save(new SkillCategory("개발"));
        SkillCategory beDetailSkill = skillCategoryRepository.save(
            new SkillCategory(mainSkill, "BE"));
        SkillCategory feDetailSkill = skillCategoryRepository.save(
            new SkillCategory(mainSkill, "FE"));
        Purpose purpose = purposeRepository.save(Purpose.from("창업"));
        Region region=regionRepository.save(Region.from("SEOUL"));
        SignUpRequest signUpRequest = AuthFixture.createSignUpRequest(
            mainSkill.getId(),
            List.of(
                new SkillRequest(beDetailSkill.getId(), SkillLevel.HIGH.getName()),
                new SkillRequest(feDetailSkill.getId(), SkillLevel.LOW.getName())
            ),
            purpose.getId(),
            region
        );

        //when
        AuthResponse authResponse = oauthService.signUp(signUpRequest);

        //then
        String memberId = jwtProvider.getPayload(authResponse.accessToken());
        Member member = memberRepository.getById(Long.valueOf(memberId));
        List<String> skillLevels = mapToSkillLevels(member);
        List<String> skillNames = mapToSkillNames(member);
        List<String> purposeNames = mapToPurposeNames(member);
        assertAll(
            () -> assertThat(member.getMainSkill().getName()).isEqualTo("개발"),
            () -> assertThat(skillLevels).contains("상", "하"),
            () -> assertThat(skillNames).contains("BE", "FE"),
            () -> assertThat(purposeNames).contains("창업"),
            () -> assertThat(authResponse.authMemberInformation().id()).isEqualTo(member.getId()),
            () -> assertThat(authResponse.authMemberInformation().nickname()).isEqualTo(
                member.getNickname()),
            () -> assertThat(authResponse.authMemberInformation().profileImageUrl()).isEqualTo(
                member.getProfileImageUrl())
        );
    }

    private List<String> mapToSkillLevels(Member member) {
        return member.getSkills().getValues().stream()
            .map(memberSkill -> memberSkill.getSkillLevel().getName())
            .toList();
    }

    private List<String> mapToSkillNames(Member member) {
        return member.getSkills().getValues().stream()
            .map(memberSkill -> memberSkill.getSkillCategory().getName())
            .toList();
    }

    private List<String> mapToPurposeNames(Member member) {
        return member.getPurposes().stream()
            .map(memberPurpose -> memberPurpose.getPurpose().getName())
            .toList();
    }

    @Test
    void 중복된_스킬으로_회원가입을_할_수_없다() {
        //given
        SkillCategory mainSkill = skillCategoryRepository.save(new SkillCategory("개발"));
        SkillCategory beDetailSkill = skillCategoryRepository.save(
            new SkillCategory(mainSkill, "BE"));
        SkillCategory feDetailSkill = skillCategoryRepository.save(
            new SkillCategory(mainSkill, "FE"));
        Purpose purpose = purposeRepository.save(Purpose.from("창업"));
        Region region=regionRepository.save(Region.from("SEOUL"));
        SignUpRequest signUpRequest = AuthFixture.createSignUpRequest(
            mainSkill.getId(),
            List.of(
                new SkillRequest(beDetailSkill.getId(), SkillLevel.HIGH.getName()),
                new SkillRequest(beDetailSkill.getId(), SkillLevel.LOW.getName()),
                new SkillRequest(feDetailSkill.getId(), SkillLevel.LOW.getName())
            ),
            purpose.getId(),
            region
        );

        //when, then
        assertThatThrownBy(() -> oauthService.signUp(signUpRequest))
            .isInstanceOf(DuplicatedSkillCategoryException.class);
    }

    @Test
    void 회원가입에_필요한_정보들을_반환한다() {
        //given
        SkillCategory 개발 = skillCategoryRepository.save(new SkillCategory("개발"));
        개발.updateParentSkill(개발);
        purposeRepository.save(Purpose.from("창업"));
        regionRepository.save(Region.from("SEOUL"));

        //when
        FindSignUpResponse findSignUpResponse = oauthService.getSignUpInFormation();

        //then
        assertAll(
            () -> assertThat(findSignUpResponse.mainSkillResponses()).hasSize(1),
            () -> assertThat(findSignUpResponse.purposeResponses()).hasSize(1),
            () -> assertThat(findSignUpResponse.regionResponses()).hasSize(1),
            () -> assertThat(findSignUpResponse.mainSkillResponses().get(0).name()).isEqualTo("개발"),
            () -> assertThat(findSignUpResponse.purposeResponses().get(0).name()).isEqualTo("창업"),
            () -> assertThat(findSignUpResponse.regionResponses().get(0).name()).isEqualTo("서울특별시")
        );
    }

}
