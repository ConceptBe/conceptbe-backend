package kr.co.conceptbe.idea.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.conceptbe.idea.IdeaLikeID;
import kr.co.conceptbe.idea.domain.IdeaLike;

@Repository
public interface IdeaLikesRepository extends JpaRepository<IdeaLike, IdeaLikeID> {
}
