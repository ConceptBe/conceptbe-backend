package kr.co.conceptbe.idea.exception;

import org.springframework.http.HttpStatus;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class IdeaLikeException extends ConceptBeException {

	public IdeaLikeException() { super(new ErrorCode(HttpStatus.CONFLICT, "이미 좋아요가 되어 있습니다")); }
}
