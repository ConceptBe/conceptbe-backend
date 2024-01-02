package kr.co.conceptbe.bookmark.service;

import org.springframework.stereotype.Service;

import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.bookmark.BookmarkID;
import kr.co.conceptbe.bookmark.repository.BookmarkRepository;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.member.Member;
import kr.co.conceptbe.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkService {

	private final IdeaRepository ideaRepository;
	private final MemberRepository memberRepository;
	private final BookmarkRepository bookmarkRepository;

	public BookmarkID addBookmark(Long ideaId, Long memberId) {
		// TODO
		// Token 통해서 유저 id 가져올 시 수정될 예정
		Member member = memberRepository.getById(memberId);
		Idea idea = ideaRepository.getById(ideaId);
		Bookmark bookmark = new Bookmark(member, idea);

		bookmarkRepository.save(bookmark);
		idea.addBookmark(bookmark);

		return bookmark.getBookmarkID();
	}
}
