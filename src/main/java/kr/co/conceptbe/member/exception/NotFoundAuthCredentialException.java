package kr.co.conceptbe.member.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class NotFoundAuthCredentialException extends ConceptBeException {

    public NotFoundAuthCredentialException(Long memberId) {
        super(new ErrorCode(UNAUTHORIZED, format("멤버 아이디가 %s인 멤버를 찾을 수 없습니다.", memberId)));
    }
}
