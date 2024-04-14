package kr.co.conceptbe.idea.exception;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ExceedIdeaSkillCategoriesSizeException extends ConceptBeException {

    public ExceedIdeaSkillCategoriesSizeException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "스킬은 최대 10개 고르실 수 있습니다."));
    }
}
