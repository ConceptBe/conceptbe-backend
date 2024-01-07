package kr.co.conceptbe.skill.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kr.co.conceptbe.common.exception.ConceptBeException;
import kr.co.conceptbe.common.exception.ErrorCode;

public class NotFoundSkillCategoryException extends ConceptBeException {

    public NotFoundSkillCategoryException(Long skillCategoryId) {
        super(new ErrorCode(BAD_REQUEST, format("스킬 아이디가 %s인 스킬을 찾을 수 없습니다.", skillCategoryId)));
    }
}
