package kr.co.conceptbe.auth.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import kr.co.conceptbe.auth.application.dto.SignUpRequest;
import kr.co.conceptbe.auth.application.dto.SignUpSkillRequest;
import kr.co.conceptbe.auth.presentation.dto.TokenResponse;
import kr.co.conceptbe.auth.support.JwtProvider;
import kr.co.conceptbe.purpose.domain.persistence.PurposeRepository;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.auth.fixture.AuthFixture;
import kr.co.conceptbe.member.persistence.MemberRepository;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
import kr.co.conceptbe.skill.domain.SkillLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class OauthServiceTest {

    @Autowired
    private OauthService oauthService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Autowired
    private PurposeRepository purposeRepository;

    @Autowired
    private JwtProvider jwtProvider;


    @Test
    void 회원가입을_할_수_있다() {
        //given
        SkillCategory mainSkill = skillCategoryRepository.save(new SkillCategory("개발"));
        SkillCategory beDetailSkill = skillCategoryRepository.save(new SkillCategory(mainSkill, "BE"));
        SkillCategory feDetailSkill = skillCategoryRepository.save(new SkillCategory(mainSkill, "FE"));
        Purpose purpose = purposeRepository.save(Purpose.from("창업"));
        SignUpRequest signUpRequest = AuthFixture.createSignUpRequest(
            mainSkill.getId(),
            List.of(
                new SignUpSkillRequest(beDetailSkill.getId(), SkillLevel.HIGH.getName()),
                new SignUpSkillRequest(feDetailSkill.getId(), SkillLevel.LOW.getName())
            ),
            purpose.getId()
        );

        //when
        TokenResponse tokenResponse = oauthService.signUp(signUpRequest);

        //then
        String memberId = jwtProvider.getPayload(tokenResponse.accessToken());
        Member member = memberRepository.getById(Long.valueOf(memberId));
        List<String> skillLevels = mapToSkillLevels(member);
        List<String> skillNames = mapToSkillNames(member);
        List<String> purposeNames = mapToPurposeNames(member);
        assertAll(
            () -> assertThat(member.getMainSkill().getName()).isEqualTo("개발"),
            () -> assertThat(skillLevels).contains("상", "하"),
            () -> assertThat(skillNames).contains("BE", "FE"),
            () -> assertThat(purposeNames).contains("창업")
        );
    }

    private List<String> mapToSkillLevels(Member member) {
        return member.getSkills().stream()
            .map(memberSkill -> memberSkill.getSkillLevel().getName())
            .toList();
    }

    private List<String> mapToSkillNames(Member member) {
        return member.getSkills().stream()
            .map(memberSkill -> memberSkill.getSkillCategory().getName())
            .toList();
    }

    private List<String> mapToPurposeNames(Member member) {
        return member.getPurposes().stream()
            .map(memberPurpose -> memberPurpose.getPurpose().getName())
            .toList();
    }
}
