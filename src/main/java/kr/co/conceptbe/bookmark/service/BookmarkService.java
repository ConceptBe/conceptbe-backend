package kr.co.conceptbe.bookmark.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.bookmark.BookmarkID;
import kr.co.conceptbe.bookmark.repository.BookmarkRepository;
import kr.co.conceptbe.bookmark.exception.BookmarkException;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkService {

	private final IdeaRepository ideaRepository;
	private final MemberRepository memberRepository;
	private final BookmarkRepository bookmarkRepository;

	public Long addBookmark(Long tokenMemberId, Long ideaId) {
		Idea idea = ideaRepository.getById(ideaId);
		Member.validateMember(tokenMemberId, idea.getCreator().getId());
		Member member = memberRepository.getById(tokenMemberId);

		BookmarkID bookmarkID = new BookmarkID(tokenMemberId, ideaId);
		Optional<Bookmark> optionalBookmark = bookmarkRepository.findById(bookmarkID);

		if(optionalBookmark.isEmpty()) {
			Bookmark bookmark = new Bookmark(bookmarkID, member, idea);
			bookmarkRepository.save(bookmark);
			idea.addBookmark(bookmark);
		} else {
			throw new BookmarkException();
		}
		return idea.getId();
	}

	public void cancelBookmark(Long tokenMemberId, Long ideaId) {
		BookmarkID bookmarkID = new BookmarkID(tokenMemberId, ideaId);
		bookmarkRepository.getById(bookmarkID);
		bookmarkRepository.deleteById(bookmarkID);
	}
}
