package kr.co.conceptbe.comment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.comment.dto.CommentCreateRequest;
import kr.co.conceptbe.comment.dto.CommentChildResponse;
import kr.co.conceptbe.comment.dto.CommentUpdateRequest;
import kr.co.conceptbe.comment.service.CommentService;
import kr.co.conceptbe.common.auth.Auth;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/{comment_id}")
	public ResponseEntity<List<CommentChildResponse>> getChildCommentList(
		@PathVariable(name = "comment_id") Long commentId) {
		List<CommentChildResponse> commentChildRespons = commentService.getChildCommentList(commentId);
		return ResponseEntity.ok(commentChildRespons);
	}

	@PostMapping("")
	public ResponseEntity<Void> createComment(
		@Auth AuthCredentials authCredentials,
		@RequestBody CommentCreateRequest request) {
		Long savedId = commentService.createComment(authCredentials.id(), request);
		return ResponseEntity.created(URI.create("/comments/" + savedId))
			.build();
	}

	@PatchMapping("/{comment_id}")
	public ResponseEntity<Void> updateComment(
		@Auth AuthCredentials authCredentials,
		@PathVariable(name = "comment_id") Long commentId,
		@RequestBody CommentUpdateRequest request) {
		Long savedId = commentService.updateComment(authCredentials.id(), commentId, request);
		return ResponseEntity.created(URI.create("/comments/" + savedId))
			.build();
	}

	@DeleteMapping("/{comment_id}")
	public ResponseEntity<Void> deleteComment(
		@Auth AuthCredentials authCredentials,
		@PathVariable(name = "comment_id") Long commentId) {
		commentService.deleteComment(authCredentials.id(), commentId);
		return ResponseEntity.noContent().build();
	}
}
