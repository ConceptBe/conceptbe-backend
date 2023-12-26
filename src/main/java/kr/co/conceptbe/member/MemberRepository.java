package kr.co.conceptbe.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByOauthId(OauthId oauthId);

    Optional<Member> findByOauthId(OauthId oauthId);
}
