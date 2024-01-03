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

import kr.co.conceptbe.comment.dto.CommentCreateRequest;
import kr.co.conceptbe.comment.dto.CommentResponse;
import kr.co.conceptbe.comment.dto.CommentUpdateRequest;
import kr.co.conceptbe.comment.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/{comment_id}")
	public ResponseEntity<List<CommentResponse>> getChildCommentList(
		@PathVariable(name = "comment_id") Long commentId) {
		List<CommentResponse> commentResponses = commentService.getChildCommentList(commentId);
		return ResponseEntity.ok(commentResponses);
	}

	@PostMapping("")
	public ResponseEntity<Void> createComment(@RequestBody CommentCreateRequest request) {
		Long savedId = commentService.createComment(request);
		return ResponseEntity.created(URI.create("/comments/" + savedId))
			.build();
	}

	@PatchMapping("/{comment_id}")
	public ResponseEntity<Void> updateComment(
		@PathVariable(name = "comment_id") Long commentId,
		@RequestBody CommentUpdateRequest request) {
		Long savedId = commentService.updateComment(commentId, request);
		return ResponseEntity.created(URI.create("/comments/" + savedId))
			.build();
	}

	@DeleteMapping("/{comment_id}")
	public ResponseEntity<Void> deleteComment(@PathVariable(name = "comment_id") Long commentId) {
		commentService.deleteComment(commentId);
		return ResponseEntity.noContent().build();
	}
}
