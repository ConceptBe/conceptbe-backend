package kr.co.conceptbe.bookmark.exception;

import org.springframework.http.HttpStatus;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class BookmarkException extends ConceptBeException {

	public BookmarkException() { super(new ErrorCode(HttpStatus.CONFLICT, "이미 스크랩 했습니다.")); }
}
