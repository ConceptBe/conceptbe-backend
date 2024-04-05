package kr.co.conceptbe.bookmark.service;

import org.springframework.stereotype.Service;

import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.bookmark.BookmarkID;
import kr.co.conceptbe.bookmark.exception.OwnerNotBookmarkException;
import kr.co.conceptbe.bookmark.repository.BookmarkRepository;
import kr.co.conceptbe.bookmark.exception.AlreadyBookmarkException;
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
		Member member = memberRepository.getById(tokenMemberId);
		if(idea.isOwner(member.getId())) {
			throw new OwnerNotBookmarkException();
		}

		BookmarkID bookmarkID = BookmarkID.of(tokenMemberId, ideaId);
		bookmarkRepository.findById(bookmarkID)
			.ifPresent(bookmark -> {
				throw new AlreadyBookmarkException();
			});

		Bookmark bookmark = Bookmark.of(bookmarkID, member, idea);
		bookmarkRepository.save(bookmark);
		idea.addBookmark(bookmark);

		return idea.getId();
	}

	public void cancelBookmark(Long tokenMemberId, Long ideaId) {
		BookmarkID bookmarkID = BookmarkID.of(tokenMemberId, ideaId);
		bookmarkRepository.getById(bookmarkID);
		bookmarkRepository.deleteById(bookmarkID);
	}
}
