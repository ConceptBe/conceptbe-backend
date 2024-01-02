package kr.co.conceptbe.bookmark.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.conceptbe.bookmark.BookmarkID;
import kr.co.conceptbe.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {

	private final BookmarkService bookmarkService;

	@PostMapping("/{idea_id}")
	public ResponseEntity<Void> addBookmark(
		@PathVariable(name = "idea_id") Long ideaId,
		@RequestParam(name = "member_id") Long memberId) {
		BookmarkID bookmarkID = bookmarkService.addBookmark(ideaId, memberId);

		return ResponseEntity.created(URI.create("/ideas/" + bookmarkID.getIdeaId()))
				.build();
	}
}
