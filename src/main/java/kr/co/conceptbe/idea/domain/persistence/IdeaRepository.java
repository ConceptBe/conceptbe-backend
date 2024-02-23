package kr.co.conceptbe.idea.domain.persistence;

import java.util.List;
import kr.co.conceptbe.idea.domain.Idea;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<Idea, Long>, IdeaRepositoryCustom {

    List<Idea> findAllByCreatorIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);

    default Idea getById(Long ideaId) {
        return findById(ideaId).orElseThrow(
                () -> new IllegalArgumentException("Not Found ID : " + ideaId));
    }
}
