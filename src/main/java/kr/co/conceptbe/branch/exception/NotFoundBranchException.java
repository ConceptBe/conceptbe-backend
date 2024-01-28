package kr.co.conceptbe.branch.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class NotFoundBranchException extends ConceptBeException {

    public NotFoundBranchException(Long branchId) {
        super(new ErrorCode(BAD_REQUEST, format("분야 아이디가 %s인 분야를 찾을 수 없습니다.", branchId)));
    }

}
