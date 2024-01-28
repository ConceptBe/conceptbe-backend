package kr.co.conceptbe.idea.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundCooperationWayException extends ConceptBeException {

    public NotFoundCooperationWayException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "존재하지 않는 협업방식입니다."));
    }

}
