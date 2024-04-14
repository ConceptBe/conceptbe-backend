package kr.co.conceptbe.idea.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidTitleLengthException extends ConceptBeException {

    public InvalidTitleLengthException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "제목은 최소 1자에서 최대 20자로 입력하셔야 합니다."));
    }
}
