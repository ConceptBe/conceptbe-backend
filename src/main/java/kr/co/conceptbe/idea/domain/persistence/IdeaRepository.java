package kr.co.conceptbe.idea.domain.persistence;

import java.util.List;
import kr.co.conceptbe.idea.domain.Idea;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<Idea, Long> {

    List<Idea> findAllByOrderByCreatedAtDesc();

    default Idea getById(Long ideaId) {
        return findById(ideaId).orElseThrow(
            () -> new IllegalArgumentException("Not Found ID : " + ideaId));
    }
}
