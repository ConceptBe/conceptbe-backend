package kr.co.conceptbe.idea.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmptyIdeaBracnhesException extends ConceptBeException {

    public EmptyIdeaBracnhesException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "분야는 최소 한 개 이상 고르셔야 합니다."));
    }
}
