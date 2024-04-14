package kr.co.conceptbe.idea.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmptyIntroduceException extends ConceptBeException {

    public EmptyIntroduceException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "본문은 필수로 입력하셔야 합니다."));
    }
}
