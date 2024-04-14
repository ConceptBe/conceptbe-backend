package kr.co.conceptbe.member.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidNicknamePatternException extends ConceptBeException {

    public InvalidNicknamePatternException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "닉네임은 한글/영어/숫자 중 구성되어야 합니다."));
    }
}
