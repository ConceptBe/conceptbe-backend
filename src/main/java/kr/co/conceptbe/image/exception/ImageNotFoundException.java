package kr.co.conceptbe.image.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ImageNotFoundException extends ConceptBeException {

    public ImageNotFoundException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "해당하는 이미지는 존재하지 않습니다."));
    }

}
