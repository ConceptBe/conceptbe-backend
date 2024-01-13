package kr.co.conceptbe.member.application;

import java.util.List;
import kr.co.conceptbe.auth.presentation.dto.TokenResponse;
import kr.co.conceptbe.auth.support.JwtProvider;
import kr.co.conceptbe.common.entity.domain.persistence.PurposeRepository;
import kr.co.conceptbe.member.Member;
import kr.co.conceptbe.member.MemberPurpose;
import kr.co.conceptbe.member.MemberRepository;
import kr.co.conceptbe.member.MemberSkillCategory;
import kr.co.conceptbe.member.application.dto.DetailSkillResponse;
import kr.co.conceptbe.member.application.dto.FindSignUpResponse;
import kr.co.conceptbe.member.application.dto.MainSkillResponse;
import kr.co.conceptbe.member.application.dto.PurposeResponse;
import kr.co.conceptbe.member.application.dto.SignUpRequest;
import kr.co.conceptbe.member.exception.AlreadyExistsNicknameException;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
import kr.co.conceptbe.skill.domain.SkillLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final SkillCategoryRepository skillCategoryRepository;
    private final PurposeRepository purposeRepository;

    public TokenResponse signUp(SignUpRequest signUpRequest) {
        Member member = saveMember(signUpRequest);
        String accessToken = jwtProvider.createAccessToken(member.getId());
        return new TokenResponse(accessToken);
    }

    private Member saveMember(SignUpRequest signUpRequest) {
        Member member = signUpRequest.toMember();
        validateDuplicatedNickName(member.getNickname());
        processSkill(signUpRequest, member);
        processPurpose(signUpRequest, member);
        return memberRepository.save(member);
    }

    public void validateDuplicatedNickName(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new AlreadyExistsNicknameException(nickname);
        }
    }

    private void processSkill(SignUpRequest signUpRequest, Member member) {
        SkillCategory mainSkill = skillCategoryRepository.getById(signUpRequest.mainSkillId());
        member.updateMainSkill(mainSkill);
        List<MemberSkillCategory> memberSkills = mapToMemberSkills(signUpRequest, member);
        for (MemberSkillCategory memberSkill : memberSkills) {
            member.addSkill(memberSkill);
        }
    }

    private List<MemberSkillCategory> mapToMemberSkills(SignUpRequest signUpRequest, Member member) {
        return signUpRequest.skills().stream()
            .map(skillRequest -> {
                SkillCategory skill = skillCategoryRepository.getById(skillRequest.skillId());
                return new MemberSkillCategory(member, skill, SkillLevel.from(skillRequest.level()));
            }).toList();
    }

    private void processPurpose(SignUpRequest signUpRequest, Member member) {
        List<MemberPurpose> memberPurposes = mapToMemberPurposes(signUpRequest, member);
        for (MemberPurpose memberPurpose : memberPurposes) {
            member.addPurpose(memberPurpose);
        }
    }

    private List<MemberPurpose> mapToMemberPurposes(SignUpRequest signUpRequest, Member member) {
        return signUpRequest.joinPurposes().stream()
            .map(joinPurposeId -> {
                Purpose purpose = purposeRepository.getById(joinPurposeId);
                return new MemberPurpose(member, purpose);
            }).toList();
    }

    public FindSignUpResponse getSignUpInFormation() {
        List<SkillCategory> skills = skillCategoryRepository.findAll();
        List<SkillCategory> mainSkills = categorizeMainSkills(skills);
        List<MainSkillResponse> mainSkillResponses = createMainSkillResponses(skills, mainSkills);

        List<PurposeResponse> purposeResponses = createPurposeResponses();

        return new FindSignUpResponse(mainSkillResponses, purposeResponses);
    }

    private List<SkillCategory> categorizeMainSkills(List<SkillCategory> skills) {
        return skills.stream()
            .filter(skill -> (skill.getId().equals(skill.getParentSkillCategory().getId())))
            .toList();
    }

    private List<MainSkillResponse> createMainSkillResponses(
        List<SkillCategory> skills,
        List<SkillCategory> mainSkills
    ) {
        return mainSkills.stream()
            .map(skill -> new MainSkillResponse(skill.getId(), skill.getName(),
                createDetailSkillResponses(skills, skill.getId())))
            .toList();
    }

    private List<DetailSkillResponse> createDetailSkillResponses(
        List<SkillCategory> skills,
        Long parentSkillId
    ) {
        return skills.stream()
            .filter(skill -> isChildSkillOfParentSkill(parentSkillId, skill))
            .map(skill -> new DetailSkillResponse(skill.getId(), skill.getName()))
            .toList();
    }

    private boolean isChildSkillOfParentSkill(Long parentSkillId, SkillCategory skill) {
        return skill.getParentSkillCategory().getId().equals(parentSkillId) && !skill.getId()
            .equals(skill.getParentSkillCategory().getId());
    }

    private List<PurposeResponse> createPurposeResponses() {
        return purposeRepository.findAll().stream()
            .map(purpose -> new PurposeResponse(purpose.getId(), purpose.getName()))
            .toList();
    }
}
