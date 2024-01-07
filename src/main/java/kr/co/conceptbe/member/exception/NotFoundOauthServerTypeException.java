package kr.co.conceptbe.member.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundOauthServerTypeException extends ConceptBeException {

    public NotFoundOauthServerTypeException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "존재하지 않는 Oauth 서버입니다. 알맞은 Oauth 서버인지 확인해주세요."));
    }
}
