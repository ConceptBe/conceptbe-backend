package kr.co.conceptbe.idea.exception;

import org.springframework.http.HttpStatus;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class NotFoundIdeaLikeException extends ConceptBeException {

	public NotFoundIdeaLikeException() {
		super(new ErrorCode(HttpStatus.NOT_FOUND, "해당 게시글은 '좋아요'하지 않았습니다."));
	}
}
