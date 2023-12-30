package kr.co.conceptbe.auth.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class TokenInvalidException extends ConceptBeException {

    public TokenInvalidException() {
        super(new ErrorCode(FORBIDDEN, "잘못된 토큰입니다. 올바른 토큰으로 다시 시도해주세요."));
    }
}
