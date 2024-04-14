package kr.co.conceptbe.idea.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmptyBranchNameException extends ConceptBeException {

    public EmptyBranchNameException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "분야는 필수로 입력되어야 합니다."));
    }
}
