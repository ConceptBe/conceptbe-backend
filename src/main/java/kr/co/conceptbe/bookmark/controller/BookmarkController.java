package kr.co.conceptbe.bookmark.controller;

import java.net.URI;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.bookmark.service.BookmarkService;
import kr.co.conceptbe.common.auth.Auth;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
@Tag(name = "Bookmark", description = "Bookmark API")
@SecurityRequirement(name = AUTHORIZATION)
public class BookmarkController {

	private final BookmarkService bookmarkService;

	@Operation(summary = "Bookmark 추가", description = "피드글을 Bookmark 합니다.")
	@PostMapping("/{ideaId}")
	public ResponseEntity<Void> addBookmark(
		@Parameter(hidden = true) @Auth AuthCredentials authCredentials,
		@PathVariable(name = "ideaId") Long ideaId) {
		Long id = bookmarkService.addBookmark(authCredentials.id(), ideaId);
		return ResponseEntity.created(URI.create("/ideas/" + id))
				.build();
	}

	@Operation(summary = "Bookmark 취소", description = "피드글을 Bookmark를 취소합니다.")
	@DeleteMapping("/{ideaId}")
	public ResponseEntity<Void> cancelBookmark(
		@Parameter(hidden = true)@Auth AuthCredentials authCredentials,
		@PathVariable(name = "ideaId") Long ideaId) {
		bookmarkService.cancelBookmark(authCredentials.id(), ideaId);
		return ResponseEntity.noContent().build();
	}
}
