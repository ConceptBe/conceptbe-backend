package kr.co.conceptbe.comment.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.comment.dto.CommentCreateRequest;
import kr.co.conceptbe.comment.dto.CommentChildResponse;
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
	public List<CommentChildResponse> getChildCommentList(Long commentId) {
		return commentRepository.getById(commentId).getComments()
			.stream()
			.sorted(Comparator.comparing(Comment::getCreatedAt))
			.map(CommentChildResponse::from)
			.toList();
	}

	public Long createComment(Long tokenMemberId, CommentCreateRequest commentCreateRequest) {
		Idea idea = ideaRepository.getById(commentCreateRequest.ideaId());
		Member member = memberRepository.getById(tokenMemberId);

		Comment comment;
		if(isParentComment(commentCreateRequest.parentId())) {
			comment = new Comment(commentCreateRequest.content(), null, member, idea);
			commentRepository.save(comment);
		} else {
			Comment parentComment = commentRepository.getById(commentCreateRequest.parentId());
			comment = new Comment(commentCreateRequest.content(), parentComment, member, idea);
			parentComment.addComment(comment);
			commentRepository.save(comment);
		}
		idea.addComment(comment);
		return comment.getId();
	}

	private boolean isParentComment(Long parentId) {
		return parentId == null || parentId == 0;
	}

	public Long updateComment(Long tokenMemberId, Long commentId, CommentUpdateRequest request) {
		Comment comment = commentRepository.getById(commentId);
		Member.validateMember(tokenMemberId, comment.getCreator().getId());
		comment.updateContent(request.content());
		return comment.getId();
	}

	public void deleteComment(Long tokenMemberId, Long commentId) {
		Comment comment = commentRepository.getById(commentId);
		Member.validateMember(tokenMemberId, comment.getCreator().getId());
		comment.updateContent("삭제된 댓글입니다.");
	}

}
