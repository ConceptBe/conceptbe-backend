package kr.co.conceptbe.member.application;

import java.util.List;
import kr.co.conceptbe.auth.presentation.dto.TokenResponse;
import kr.co.conceptbe.auth.support.JwtProvider;
import kr.co.conceptbe.member.Member;
import kr.co.conceptbe.member.MemberRepository;
import kr.co.conceptbe.member.MemberSkillCategory;
import kr.co.conceptbe.member.application.dto.SignUpRequest;
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

    public TokenResponse signUp(SignUpRequest signUpRequest) {
        Member member = saveMember(signUpRequest);
        String accessToken = jwtProvider.createAccessToken(member.getId());
        return new TokenResponse(accessToken);
    }

    private Member saveMember(SignUpRequest signUpRequest) {
        Member member = signUpRequest.toMember();
        SkillCategory mainSkill = skillCategoryRepository.getById(signUpRequest.mainSkillId());
        member.updateMainSkill(mainSkill);
        List<MemberSkillCategory> memberSkills = mapToMemberSkill(signUpRequest, member);
        for (MemberSkillCategory memberSkill : memberSkills) {
            member.addSkill(memberSkill);
        }

        //TODO 대표스킬, 스킬들, 목적 저장 추가
        //        List<Long> joinPurposes,
        //        Long livingPlace,

        return memberRepository.save(member);
    }

    private List<MemberSkillCategory> mapToMemberSkill(SignUpRequest signUpRequest, Member member) {
        return signUpRequest.skills().stream()
            .map((skillRequest) -> {
                SkillCategory skill = skillCategoryRepository.getById(skillRequest.skillId());
                return new MemberSkillCategory(member, skill, SkillLevel.from(skillRequest.level()));
            }).toList();
    }
}
