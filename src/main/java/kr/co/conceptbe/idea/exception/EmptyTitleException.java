package kr.co.conceptbe.idea.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmptyTitleException extends ConceptBeException {

    public EmptyTitleException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "제목은 필수로 입력하셔야 합니다."));
    }
}
