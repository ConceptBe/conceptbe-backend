package kr.co.conceptbe.image.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ImagesEmptyException extends ConceptBeException {

    public ImagesEmptyException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "이미지는 최소 한 개 이상 업로드 해야합니다."));
    }

}
