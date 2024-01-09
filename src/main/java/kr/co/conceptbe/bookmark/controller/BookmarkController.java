package kr.co.conceptbe.bookmark.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.bookmark.service.BookmarkService;
import kr.co.conceptbe.common.auth.Auth;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {

	private final BookmarkService bookmarkService;

	@PostMapping("/{ideaId}")
	public ResponseEntity<Void> addBookmark(
		@Auth AuthCredentials authCredentials,
		@PathVariable(name = "ideaId") Long ideaId) {
		Long id = bookmarkService.addBookmark(authCredentials.id(), ideaId);
		return ResponseEntity.created(URI.create("/ideas/" + id))
				.build();
	}

	@DeleteMapping("/{ideaId}")
	public ResponseEntity<Void> cancelBookmark(
		@Auth AuthCredentials authCredentials,
		@PathVariable(name = "ideaId") Long ideaId) {
		bookmarkService.cancelBookmark(authCredentials.id(), ideaId);
		return ResponseEntity.noContent().build();
	}
}
