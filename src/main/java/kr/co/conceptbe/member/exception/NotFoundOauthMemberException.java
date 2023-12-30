package kr.co.conceptbe.member.exception;

import static java.lang.String.format;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundOauthMemberException extends ConceptBeException {

    public NotFoundOauthMemberException(String oauthId) {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, format("Oauth 아이디가 %s인 멤버를 찾을 수 없습니다.", oauthId)));
    }
}
