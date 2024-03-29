package kr.co.conceptbe.comment.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.comment.CommentLike;
import kr.co.conceptbe.comment.dto.CommentCreateRequest;
import kr.co.conceptbe.comment.dto.CommentChildResponse;
import kr.co.conceptbe.comment.dto.CommentUpdateRequest;
import kr.co.conceptbe.comment.exception.CommentLikeException;
import kr.co.conceptbe.comment.repository.CommentLikeRepository;
import kr.co.conceptbe.comment.repository.CommentRepository;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

	private final MemberRepository memberRepository;
	private final IdeaRepository ideaRepository;
	private final CommentRepository commentRepository;
	private final CommentLikeRepository commentLikeRepository;

	@Transactional(readOnly = true)
	public List<CommentChildResponse> getChildCommentList(Long memberId, Long commentId) {
		return commentRepository.getById(commentId).getComments()
			.stream()
			.sorted(Comparator.comparing(Comment::getCreatedAt))
			.map(comment -> CommentChildResponse.of(comment, memberId))
			.toList();
	}

	public Long createComment(Long tokenMemberId, CommentCreateRequest commentCreateRequest) {
		Idea idea = ideaRepository.getById(commentCreateRequest.ideaId());
		Member member = memberRepository.getById(tokenMemberId);

		Comment comment;
		if(isParentComment(commentCreateRequest.parentId())) {
			comment = new Comment(commentCreateRequest.content(), null, member, idea);
			idea.addComment(comment);
		} else {
			Comment parentComment = commentRepository.getById(commentCreateRequest.parentId());
			comment = new Comment(commentCreateRequest.content(), parentComment, member, idea);
			parentComment.addComment(comment);
		}
		commentRepository.save(comment);
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
		comment.commentDelete();
	}

	public Long likesComment(Long tokenMemberId, Long commentId) {
		Member member = memberRepository.getById(tokenMemberId);
		Comment comment = commentRepository.getById(commentId);

		Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByMemberAndComment(member, comment);
		if(optionalCommentLike.isEmpty()) {
			CommentLike commentLike = new CommentLike(member, comment);
			comment.addCommentLike(commentLike);
			commentLikeRepository.save(commentLike);
		} else {
			throw new CommentLikeException();
		}
		return comment.getId();
	}

	public void likesCancelComment(Long tokenMemberId, Long commentId) {
		Member member = memberRepository.getById(tokenMemberId);
		Comment comment = commentRepository.getById(commentId);
		commentLikeRepository.findByMemberAndComment(member, comment).ifPresent(commentLikeRepository::delete);
	}
}
