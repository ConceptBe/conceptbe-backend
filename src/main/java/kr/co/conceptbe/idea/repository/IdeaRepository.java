package kr.co.conceptbe.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.conceptbe.idea.Idea;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
}
