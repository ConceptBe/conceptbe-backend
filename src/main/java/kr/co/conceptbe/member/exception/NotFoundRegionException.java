package kr.co.conceptbe.member.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundRegionException extends ConceptBeException {

    public NotFoundRegionException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "존재하지 않는 지역입니다. 알맞은 지역인지 확인해주세요."));
    }
}
