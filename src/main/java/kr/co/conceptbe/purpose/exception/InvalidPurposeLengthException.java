package kr.co.conceptbe.purpose.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidPurposeLengthException extends ConceptBeException {

    public InvalidPurposeLengthException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "목적의 이름은 최소 1자 이상이어야 합니다."));
    }
}
