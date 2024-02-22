package kr.co.conceptbe.idea.domain.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import kr.co.conceptbe.idea.domain.Idea;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<Idea, Long> {

    List<Idea> findAllByOrderByCreatedAtDesc(Pageable pageable);

    List<Idea> findAllByOrderByLikesDesc(Pageable pageable);

    List<Idea> findAllByCreatorIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);

    default Idea getById(Long ideaId) {
        return findById(ideaId).orElseThrow(
            () -> new IllegalArgumentException("Not Found ID : " + ideaId));
    }
}
