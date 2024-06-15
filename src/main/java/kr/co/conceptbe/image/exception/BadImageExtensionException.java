package kr.co.conceptbe.image.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class BadImageExtensionException extends ConceptBeException {

    public BadImageExtensionException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "업로드 가능한 확장자가 아닙니다."));
    }

}
