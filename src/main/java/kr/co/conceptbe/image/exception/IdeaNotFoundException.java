package kr.co.conceptbe.image.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class IdeaNotFoundException extends ConceptBeException {

    public IdeaNotFoundException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "이미지를 추가할 게시글을 찾을 수 없습니다."));
    }

}
