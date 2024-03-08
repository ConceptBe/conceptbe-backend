package kr.co.conceptbe.comment.exception;

import org.springframework.http.HttpStatus;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class CommentLikeException extends ConceptBeException {
	public CommentLikeException() {
		super(new ErrorCode(HttpStatus.CONFLICT, "해당 댓글을 이미 '좋아요'했습니다."));
	}
}
