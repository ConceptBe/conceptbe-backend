package kr.co.conceptbe.idea.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidLengthIntroduceException extends ConceptBeException {

    public InvalidLengthIntroduceException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "본문은 최소 10자에서 최대 2000자로 입력하셔야 합니다."));
    }
}
