package kr.co.conceptbe.member.application;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.conceptbe.member.application.dto.GetMemberProfileResponse;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.exception.AlreadyExistsNicknameException;
import kr.co.conceptbe.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void validateDuplicatedNickName(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new AlreadyExistsNicknameException(nickname);
        }
    }

    public GetMemberProfileResponse getMemberProfileBy(Long id) {
        Member member = memberRepository.getById(id);
        return new GetMemberProfileResponse(
            member.getProfileImageUrl(),
            member.getNickname(),
            member.getMainSkill().getName(),
            member.getWorkingPlace(),
            member.getWorkingPlace(),
            member.getIntroduce(),
            mapToMemberSkills(member),
            mapToMemberPurposes(member)
        );
    }

    private List<String> mapToMemberPurposes(Member member) {
        return member.getPurposes().stream()
            .map(purpose -> purpose.getPurpose().getName())
            .collect(Collectors.toList());
    }

    private List<String> mapToMemberSkills(Member member) {
        return member.getSkills().stream()
            .map(skill -> skill.getSkillCategory().getName())
            .collect(Collectors.toList());
    }
}
