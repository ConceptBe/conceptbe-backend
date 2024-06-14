package kr.co.conceptbe.image.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ExceedImageSizeException extends ConceptBeException {

    public ExceedImageSizeException(int size) {
        super(new ErrorCode(
            HttpStatus.BAD_REQUEST,
            String.format("이미지는 최대 %d개를 초과할 수 없습니다.", size)
        ));
    }
}
