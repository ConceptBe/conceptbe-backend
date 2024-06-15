package kr.co.conceptbe.image.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidImagesSizeException extends ConceptBeException {

    public InvalidImagesSizeException(int imageSizeUpperBound) {
        super(new ErrorCode(
                HttpStatus.BAD_REQUEST,
                String.format("이미지는 최소 0 ~ %d개까지 가능합니다.", imageSizeUpperBound)
        ));
    }

}
