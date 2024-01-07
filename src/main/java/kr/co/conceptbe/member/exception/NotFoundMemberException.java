package kr.co.conceptbe.member.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class NotFoundMemberException extends ConceptBeException {

    public NotFoundMemberException(Long memberId) {
        super(new ErrorCode(BAD_REQUEST, format("멤버 아이디가 %s인 멤버를 찾을 수 없습니다.", memberId)));
    }
}
