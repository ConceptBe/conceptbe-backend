package kr.co.conceptbe.member.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class AlreadyExistsNicknameException extends ConceptBeException {

    public AlreadyExistsNicknameException(String nickname) {
        super(new ErrorCode(BAD_REQUEST, format("$s는 이미 존재하는 닉네임입니다.", nickname)));
    }
}
