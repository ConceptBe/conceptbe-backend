package kr.co.conceptbe.idea.domain.persistence;

import kr.co.conceptbe.idea.domain.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<Idea, Long> {
}
