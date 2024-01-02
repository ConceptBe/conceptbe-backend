package kr.co.conceptbe.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.bookmark.BookmarkID;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkID> {
}
