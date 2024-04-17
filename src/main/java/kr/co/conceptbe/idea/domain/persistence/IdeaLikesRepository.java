package kr.co.conceptbe.idea.domain.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaLike;
import kr.co.conceptbe.member.domain.Member;

@Repository
public interface IdeaLikesRepository extends JpaRepository<IdeaLike, Long> {

	Optional<IdeaLike> findByMemberAndIdea(Member member, Idea idea);

	void deleteByMemberAndIdea(Member member, Idea idea);

	default IdeaLike getById(Long id) {
		return findById(id).orElseThrow(
			() -> new IllegalArgumentException("Not Found ID : " + id));
	}
}
