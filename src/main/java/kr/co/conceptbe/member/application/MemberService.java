package kr.co.conceptbe.member.application;

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
}
