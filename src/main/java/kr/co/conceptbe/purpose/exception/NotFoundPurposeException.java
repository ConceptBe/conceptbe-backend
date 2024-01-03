package kr.co.conceptbe.purpose.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class NotFoundPurposeException extends ConceptBeException {

    public NotFoundPurposeException(Long purposeId) {
        super(new ErrorCode(BAD_REQUEST, format("목적 아이디가 %s인 목적을 찾을 수 없습니다.", purposeId)));
    }
}
