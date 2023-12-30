package kr.co.conceptbe.auth.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class TokenExpiredException extends ConceptBeException {

    public TokenExpiredException() {
        super(new ErrorCode(UNAUTHORIZED, "만료된 토큰입니다. 올바른 토큰으로 다시 시도해주세요."));
    }
}
