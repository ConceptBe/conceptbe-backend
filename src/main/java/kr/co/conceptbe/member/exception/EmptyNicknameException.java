package kr.co.conceptbe.member.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmptyNicknameException extends ConceptBeException {

    public EmptyNicknameException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "닉네임은 필수로 입력하셔야 합니다."));
    }
}
