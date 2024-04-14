package kr.co.conceptbe.branch.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidBranchLengthException extends ConceptBeException {

    public InvalidBranchLengthException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "분야의 이름은 최소 1자 이상이어야 합니다."));
    }
}
