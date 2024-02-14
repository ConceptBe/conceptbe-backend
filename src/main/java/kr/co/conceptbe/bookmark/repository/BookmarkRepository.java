package kr.co.conceptbe.bookmark.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.bookmark.BookmarkID;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkID> {

	List<Bookmark> findAllByMemberIdOrderByIdeaCreatedAtDesc(Long memberId, Pageable pageable);

	default Bookmark getById(BookmarkID bookmarkID) {
		return findById(bookmarkID).orElseThrow(
			() -> new IllegalArgumentException("Not Found ID : " + bookmarkID));
	}
}
