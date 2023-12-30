package kr.co.conceptbe.dnd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.conceptbe.common.entity.utils.CommonResponse;
import kr.co.conceptbe.dnd.dto.request.CommentCreateRequest;
import kr.co.conceptbe.dnd.dto.response.CommentResponse;
import kr.co.conceptbe.dnd.dto.request.CommentUpdateRequest;
import kr.co.conceptbe.dnd.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/{comment_id}")
	public ResponseEntity<CommonResponse.ListResponse<CommentResponse>> getChildCommentList(
			@PathVariable(name = "comment_id") Long commentId) {
		return commentService.getChildCommentList(commentId);
	}

	@PostMapping("")
	public ResponseEntity<CommonResponse> createComment(@RequestBody CommentCreateRequest request) {
		return commentService.createComment(request);
	}

	@PatchMapping("/{comment_id}")
	public ResponseEntity<CommonResponse> updateComment(
		@PathVariable(name = "comment_id") Long commentId,
		@RequestBody CommentUpdateRequest request) {
		return commentService.updateComment(commentId, request);
	}

	@DeleteMapping("/{comment_id}")
	public ResponseEntity<CommonResponse> deleteComment(@PathVariable(name = "comment_id") Long commentId) {
		return commentService.deleteComment(commentId);
	}
}
