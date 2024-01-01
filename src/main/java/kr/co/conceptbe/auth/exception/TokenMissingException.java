package kr.co.conceptbe.auth.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class TokenMissingException extends ConceptBeException {

    public TokenMissingException() {
        super(new ErrorCode(UNAUTHORIZED, "토큰이 필요합니다."));
    }
}
