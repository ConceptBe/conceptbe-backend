package kr.co.conceptbe.purpose.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmptyPurposeNameException extends ConceptBeException {

    public EmptyPurposeNameException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "목적은 필수로 입력되어야 합니다."));
    }
}
