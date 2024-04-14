package kr.co.conceptbe.member.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidNicknameLengthException extends ConceptBeException {

    public InvalidNicknameLengthException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "닉네임은 2글자에서 10글자 사이어야 합니다."));
    }
}
