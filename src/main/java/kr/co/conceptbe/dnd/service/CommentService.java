package kr.co.conceptbe.dnd.service;

import static kr.co.conceptbe.common.entity.utils.CommonResponse.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.common.entity.utils.CommonResponse;
import kr.co.conceptbe.dnd.dto.request.CommentCreateRequest;
import kr.co.conceptbe.dnd.dto.response.CommentResponse;
import kr.co.conceptbe.dnd.dto.request.CommentUpdateRequest;
import kr.co.conceptbe.dnd.repository.CommentRepository;
import kr.co.conceptbe.dnd.repository.IdeaRepository;
import kr.co.conceptbe.idea.Idea;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final IdeaRepository ideaRepository;
	private final CommentRepository commentRepository;

	public ResponseEntity<ListResponse<CommentResponse>> getChildCommentList(Long commentId) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException("Not Found ID : " + commentId));
		List<CommentResponse> commentResponseList = new ArrayList<>();
		for (Comment childComment : comment.getComments()) {
			int likesCnt = childComment.getCommentLikes().size();

			CommentResponse commentResponse = CommentResponse.builder()
				.nickname(childComment.getCreator().getNickname())
				.memberSkillList(childComment.getCreator().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList())
				.content(childComment.getContent())
				.build();
			commentResponse.setLikesCount(likesCnt > 999 ? "999+" : String.valueOf(likesCnt));

			commentResponseList.add(commentResponse);
		}
		return toListResponse("댓글 조회 성공", HttpStatus.OK.value(), commentResponseList);
	}

	@Transactional
	public ResponseEntity<CommonResponse> createComment(CommentCreateRequest commentCreateRequest) {
		Idea idea = ideaRepository.findById(commentCreateRequest.getIdeaId())
			.orElseThrow(() -> new IllegalArgumentException("Not Found ID : " + commentCreateRequest.getIdeaId()));

		if(commentCreateRequest.getParentId() != null) {
			Comment parentComment = commentRepository.findById(commentCreateRequest.getIdeaId())
				.orElseThrow(() -> new IllegalArgumentException("Not Found ID : " + commentCreateRequest.getParentId()));

			Comment comment = Comment.builder()
				.content(commentCreateRequest.getContent())
				.parentComment(parentComment)
				// .creator(commentRequest.getUserId())
				.idea(idea)
				.build();
			commentRepository.save(comment);
			parentComment.addComment(comment);
		} else {
			Comment comment = Comment.builder()
				.content(commentCreateRequest.getContent())
				.parentComment(null)
				// .creator(commentRequest.getUserId())
				.idea(idea)
				.build();
			commentRepository.save(comment);
		}

		return toCommonResponse("댓글 작성", HttpStatus.OK.value());
	}

	@Transactional
	public ResponseEntity<CommonResponse> updateComment(Long commentId, CommentUpdateRequest request) {
		// 댓글 주인이 userId 인지 확인하는 로직 추가 예정
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException("Not Found ID : " + commentId));
		comment.updateContent(request.getContent());

		return toCommonResponse("댓글 수정", HttpStatus.OK.value());
	}

	@Transactional
	public ResponseEntity<CommonResponse> deleteComment(Long commentId) {
		// 댓글 주인이 userId 인지 확인하는 로직 추가 예정
		// 댓글에 삭제 판단 하기 추가?
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new IllegalArgumentException("Not Found ID : " + commentId));
		comment.updateContent("삭제된 댓글입니다.");

		return toCommonResponse("댓글 삭제", HttpStatus.OK.value());
	}

}
