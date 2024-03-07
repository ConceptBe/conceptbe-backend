package kr.co.conceptbe.comment.controller;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.comment.dto.CommentCreateRequest;
import kr.co.conceptbe.comment.dto.CommentChildResponse;
import kr.co.conceptbe.comment.dto.CommentUpdateRequest;
import kr.co.conceptbe.comment.service.CommentService;
import kr.co.conceptbe.common.auth.Auth;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Tag(name = "Comment", description = "Comment API")
@SecurityRequirement(name = AUTHORIZATION)
public class CommentController {

	private final CommentService commentService;

	@Operation(summary = "자식 댓글 가져오기", description = "부모 댓글에 대해 자식 댓글을 가져옵니다.")
	@GetMapping("/{commentId}")
	public ResponseEntity<List<CommentChildResponse>> getChildCommentList(
		@Parameter(hidden = true) @Auth AuthCredentials authCredentials,
		@PathVariable(name = "commentId") Long commentId) {
		List<CommentChildResponse> commentChildRespons = commentService.getChildCommentList(authCredentials.id(), commentId);
		return ResponseEntity.ok(commentChildRespons);
	}

	@Operation(summary = "댓글 쓰기", description = "피드글에 댓글을 씁니다.")
	@PostMapping
	public ResponseEntity<Void> createComment(
		@Parameter(hidden = true) @Auth AuthCredentials authCredentials,
		@Valid @RequestBody CommentCreateRequest request) {
		Long savedId = commentService.createComment(authCredentials.id(), request);
		return ResponseEntity.created(URI.create("/comments/" + savedId))
			.build();
	}

	@Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
	@PatchMapping("/{commentId}")
	public ResponseEntity<Void> updateComment(
		@Parameter(hidden = true) @Auth AuthCredentials authCredentials,
		@PathVariable(name = "commentId") Long commentId,
		@Valid @RequestBody CommentUpdateRequest request) {
		Long savedId = commentService.updateComment(authCredentials.id(), commentId, request);
		return ResponseEntity.created(URI.create("/comments/" + savedId))
			.build();
	}

	@Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> deleteComment(
		@Parameter(hidden = true) @Auth AuthCredentials authCredentials,
		@PathVariable(name = "commentId") Long commentId) {
		commentService.deleteComment(authCredentials.id(), commentId);
		return ResponseEntity.noContent().build();
	}
}
