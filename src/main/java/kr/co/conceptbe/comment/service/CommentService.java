package kr.co.conceptbe.comment.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.comment.dto.CommentCreateRequest;
import kr.co.conceptbe.comment.dto.CommentResponse;
import kr.co.conceptbe.comment.dto.CommentUpdateRequest;
import kr.co.conceptbe.comment.repository.CommentRepository;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.member.Member;
import kr.co.conceptbe.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

	private final MemberRepository memberRepository;
	private final IdeaRepository ideaRepository;
	private final CommentRepository commentRepository;

	@Transactional(readOnly = true)
	public List<CommentResponse> getChildCommentList(Long commentId) {
		return commentRepository.getById(commentId).getComments()
			.stream()
			.sorted(Comparator.comparing(Comment::getCreatedAt))
			.map(CommentResponse::from)
			.toList();
	}

	public Long createComment(CommentCreateRequest commentCreateRequest) {
		Idea idea = ideaRepository.getById(commentCreateRequest.ideaId());
		Member member = memberRepository.getById(commentCreateRequest.userId());

		Comment comment;
		if(commentCreateRequest.parentId() != null) {
			Comment parentComment = commentRepository.getById(commentCreateRequest.parentId());
			comment = new Comment(commentCreateRequest.content(), parentComment,
				member, idea, new ArrayList<>(), new ArrayList<>());
			commentRepository.save(comment);
			parentComment.addComment(comment);
		} else {
			comment = new Comment(commentCreateRequest.content(), null,
				member, idea, new ArrayList<>(), new ArrayList<>());
			commentRepository.save(comment);
		}

		return comment.getId();
	}

	public Long updateComment(Long commentId, CommentUpdateRequest request) {
		// TODO
		// 댓글 주인이 userId 인지 확인하는 로직 추가 예정
		Comment comment = commentRepository.getById(commentId);
		comment.updateContent(request.content());

		return comment.getId();
	}

	public void deleteComment(Long commentId) {
		// TODO
		// 댓글 주인이 userId 인지 확인하는 로직 추가 예정
		// 댓글에 삭제 판단 하기 추가?
		Comment comment = commentRepository.getById(commentId);
		comment.updateContent("삭제된 댓글입니다.");
	}

}
