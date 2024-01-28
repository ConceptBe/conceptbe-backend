package kr.co.conceptbe.member.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class UnAuthorizedMemberException extends ConceptBeException {

    public UnAuthorizedMemberException() {
        super(new ErrorCode(BAD_REQUEST, "인증되지 않은 사용자입니다."));
    }

}
