package kr.co.conceptbe.member;

import java.util.Optional;
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

    boolean existsByNickname(String nickname);
}
