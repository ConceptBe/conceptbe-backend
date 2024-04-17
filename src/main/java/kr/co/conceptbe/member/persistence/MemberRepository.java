package kr.co.conceptbe.member.persistence;

import java.util.Optional;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.domain.OauthId;
import kr.co.conceptbe.member.domain.vo.Nickname;
import kr.co.conceptbe.member.exception.NotFoundMemberException;
import kr.co.conceptbe.member.exception.NotFoundOauthMemberException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByOauthId(OauthId oauthId);

    Optional<Member> findByOauthId(OauthId oauthId);

    default Member getByOauthId(OauthId oauthId) {
        return findByOauthId(oauthId).orElseThrow(
            () -> new NotFoundOauthMemberException(oauthId.getOauthServerId()));
    }

    default Member getById(Long memberId) {
        return findById(memberId).orElseThrow(
            () -> new NotFoundMemberException(memberId));
    }

    boolean existsByNickname(Nickname nickname);
}
