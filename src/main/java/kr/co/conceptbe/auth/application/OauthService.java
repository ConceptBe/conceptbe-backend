package kr.co.conceptbe.auth.application;

import java.util.List;
import kr.co.conceptbe.auth.application.dto.AuthMemberInformation;
import kr.co.conceptbe.auth.application.dto.AuthResponse;
import kr.co.conceptbe.auth.application.dto.DetailSkillResponse;
import kr.co.conceptbe.auth.application.dto.FindSignUpResponse;
import kr.co.conceptbe.auth.application.dto.MainSkillResponse;
import kr.co.conceptbe.auth.application.dto.OauthMemberResponse;
import kr.co.conceptbe.auth.application.dto.PurposeResponse;
import kr.co.conceptbe.auth.application.dto.SignUpRequest;
import kr.co.conceptbe.auth.domain.authcode.AuthCodeRequestUrlProviderHandler;
import kr.co.conceptbe.auth.domain.client.OauthMemberClientHandler;
import kr.co.conceptbe.auth.infra.oauth.dto.OauthMemberInformation;
import kr.co.conceptbe.auth.support.JwtProvider;
import kr.co.conceptbe.idea.application.response.RegionResponse;
import kr.co.conceptbe.member.application.MemberService;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.domain.MemberPurpose;
import kr.co.conceptbe.member.domain.OauthId;
import kr.co.conceptbe.member.domain.OauthServerType;
import kr.co.conceptbe.member.domain.vo.Nickname;
import kr.co.conceptbe.member.persistence.MemberRepository;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.purpose.domain.persistence.PurposeRepository;
import kr.co.conceptbe.region.domain.presentation.RegionRepository;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
import kr.co.conceptbe.skill.domain.SkillLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OauthService {

    private final MemberRepository memberRepository;
    private final OauthMemberClientHandler oauthMemberClientHandler;
    private final AuthCodeRequestUrlProviderHandler authCodeRequestUrlProviderHandler;
    private final JwtProvider jwtProvider;
    private final MemberService memberService;
    private final SkillCategoryRepository skillCategoryRepository;
    private final PurposeRepository purposeRepository;
    private final RegionRepository regionRepository;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderHandler.provideUrl(oauthServerType);
    }

    //TODO 외부와 Transactional 분리 예정
    public OauthMemberResponse getOauthMemberInformationBy(OauthServerType oauthServerType,
        String code) {
        OauthMemberInformation oauthMemberInformation = oauthMemberClientHandler.getOauthMemberInformation(
            oauthServerType, code);
        boolean isMember = memberRepository.existsByOauthId(
            new OauthId(String.valueOf(oauthMemberInformation.oauthId()), oauthServerType)
        );
        return new OauthMemberResponse(isMember, oauthMemberInformation);
    }

    public AuthResponse login(String oauthId, OauthServerType oauthServerType) {
        Member member = memberRepository.getByOauthId(new OauthId(oauthId, oauthServerType));
        String accessToken = jwtProvider.createAccessToken(member.getId());
        return new AuthResponse(
            accessToken,
            new AuthMemberInformation(member.getId(), member.getNickname(),
                member.getProfileImageUrl())
        );
    }

    public AuthResponse signUp(SignUpRequest signUpRequest) {
        Member member = saveMember(signUpRequest);
        String accessToken = jwtProvider.createAccessToken(member.getId());
        return new AuthResponse(
            accessToken,
            new AuthMemberInformation(member.getId(), member.getNickname(),
                member.getProfileImageUrl())
        );
    }

    private Member saveMember(SignUpRequest signUpRequest) {
        Member member = toMember(signUpRequest);
        memberService.validateDuplicatedNickName(member.getNickname());
        processSkill(signUpRequest, member);
        processPurpose(signUpRequest, member);
        return memberRepository.save(member);
    }

    private Member toMember(SignUpRequest signUpRequest) {
        return new Member(
            new OauthId(signUpRequest.oauthId(),
                OauthServerType.from(signUpRequest.oauthServerType())),
            Nickname.from(signUpRequest.nickname()),
            signUpRequest.profileImageUrl(),
            signUpRequest.email(),
            signUpRequest.introduction(),
            signUpRequest.workingPlace(),
            regionRepository.getById(signUpRequest.livingPlaceId())
        );
    }

    private void processSkill(SignUpRequest signUpRequest, Member member) {
        SkillCategory mainSkill = skillCategoryRepository.getById(signUpRequest.mainSkillId());
        member.updateMainSkill(mainSkill);

        List<SkillCategory> skillCategories = mapToSkillCategories(signUpRequest);
        List<SkillLevel> skillLevels = mapToSkillLevels(signUpRequest);
        member.addSkills(member, skillCategories, skillLevels);
    }

    private List<SkillCategory> mapToSkillCategories(SignUpRequest signUpRequest) {
        return signUpRequest.skills().stream()
            .map(skillRequest -> skillCategoryRepository.getById(skillRequest.skillId()))
            .toList();
    }

    private List<SkillLevel> mapToSkillLevels(SignUpRequest signUpRequest) {
        return signUpRequest.skills().stream()
            .map(skillRequest -> SkillLevel.from(skillRequest.level()))
            .toList();
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
        List<RegionResponse> regionResponses = createRegionResponses();

        return new FindSignUpResponse(mainSkillResponses, purposeResponses, regionResponses);
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

    private List<RegionResponse> createRegionResponses() {
        return regionRepository.findAll().stream()
            .map(RegionResponse::from)
            .toList();
    }
}
