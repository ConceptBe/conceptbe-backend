package kr.co.conceptbe.bookmark.exception;

import org.springframework.http.HttpStatus;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class OwnerNotBookmarkException extends ConceptBeException {
	public OwnerNotBookmarkException() { super(new ErrorCode(HttpStatus.BAD_REQUEST, "본인 글을 스크랩할 수 없습니다.")); }
}
