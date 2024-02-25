package kr.co.conceptbe.member.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class NotOwnerException extends ConceptBeException {

    public NotOwnerException(Long memberId) {
        super(new ErrorCode(BAD_REQUEST, format("$s는 해당 주인이 아닙니다.", memberId)));
    }
}
